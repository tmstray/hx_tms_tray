package com.huaxin.cloud.tms.tray.printer;


import com.huaxin.cloud.tms.tray.printer.config.ParamConfigSerial;
import com.huaxin.cloud.tms.tray.printer.exception.SerialException;
import com.huaxin.cloud.tms.tray.printer.service.IReceiveHander;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;

/**
 * 串口数据交互处理类，负责数据的发送及接收
 * @author Admin
 *
 */
public class SerialPortListener extends Thread implements SerialPortEventListener{
	//串口对象
	private SerialPort serialPort;
	// 输入流
	private InputStream inputStream;
	// 输出流
	private OutputStream outputStream;
	// 阻塞对象
	private Object object = new Object();
	// 返回异常
	private SerialException exception;
	//返回值缓冲区
	private ByteArrayOutputStream ba = new ByteArrayOutputStream();
	//返回等待
	private Timer timer = null;

	private volatile boolean hasRev = false;

	private IReceiveHander receiveHander;

	public void setReceiveHander(IReceiveHander receiveHander) {
		this.receiveHander = receiveHander;
	}

	/**
	 * 初始化串口
	 * @param paramConfigSerial
	 * @throws SerialException
	 */
	public void init(ParamConfigSerial paramConfigSerial) throws SerialException {
		try {
			RXTXLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new SerialException("加载serial库异常:"+e.getMessage());
		}
		try {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(paramConfigSerial.getSerialNumber());//COM4是串口名字
			serialPort = (SerialPort)portIdentifier.open(paramConfigSerial.getSerialNumber(), 2000);    //2000是打开超时时间
			// 设置串口通讯参数:波特率，数据位，停止位,校验方式
			serialPort.setSerialPortParams(paramConfigSerial.getBaudRate(), paramConfigSerial.getDataBit(),
					paramConfigSerial.getStopBit(), paramConfigSerial.getCheckoutBit());
		}catch(Exception ex) {
			ex.printStackTrace();
			throw new SerialException("打开端口失败:" + ex.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			// 设置串口监听
			serialPort.addEventListener(this);
			// 设置串口数据时间有效(可监听)
			serialPort.notifyOnDataAvailable(true);
			serialPort.notifyOnOverrunError(true);
			serialPort.notifyOnDSR(true);
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 实现接口SerialPortEventListener中的方法 读取从串口中接收的数据
	 */
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI: // 通讯中断
			System.out.println("BI");
			break;
		case SerialPortEvent.OE: // 溢位错误
			System.out.println("OE");
			break;
		case SerialPortEvent.FE: // 帧错误
			System.out.println("FE");
			break;
		case SerialPortEvent.PE: // 奇偶校验错误
			System.out.println("PE");
			break;
		case SerialPortEvent.CD: // 载波检测
			System.out.println("CD");
			break;
		case SerialPortEvent.CTS: // 清除发送
			System.out.println("CTS");
			break;
		case SerialPortEvent.DSR: // 数据设备准备好
			System.out.println("DSR");
			break;
		case SerialPortEvent.RI: // 响铃侦测
			System.out.println("RI");
			break;
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 输出缓冲区已清空
			System.out.println("OUTPUT_BUFFER_EMPTY");
			break;
		case SerialPortEvent.DATA_AVAILABLE: // 有数据到达
			read();
			break;
		default:
			break;
		}
	}

	/**
	 * 读取返回值
	 */
	private void read(){
		exception = null;
		if(timer != null) {
			if(!hasRev) { //间隔小于指定值，不结束继续读取
				timer.cancel();
				timer = null;
			}else { //间隔大于指定值，数据已经返回了，丢弃数据
				if(this.receiveHander == null)
					return;
			}
		}
		try {
			inputStream = serialPort.getInputStream();
			// 通过输入流对象的available方法获取数组字节长度
			byte[] revData = new byte[inputStream.available()];
			IOUtils.readFully(inputStream, revData);
			ba.write(revData);


			/**
			 * 设备可能多次写入，需要多次读取合并返回结果，但是多次写入的间隔是有限制的
			 */
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					synchronized (object) {
						try {
							hasRev = true;
							object.notify();
							if(receiveHander != null) {
								receiveHander.received(ba.toByteArray());
								rest();
							}
						} catch (Exception e) {
							e.printStackTrace();
							rest();
						}
					}
				}
			} , 100);

		} catch (IOException e) {
			exception = new SerialException("读取串口数据时发生IO异常");
		}finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {}
			}
		}
	}

	/**
	 * 发送指令无返回值
	 * @param data
	 * @return
	 * @throws SerialException
	 */
	public synchronized byte[] sendl(byte[] data) throws SerialException {
		try {
			rest();

			outputStream = serialPort.getOutputStream();
			outputStream.write(data);
			outputStream.flush();

			synchronized (object) {
				try {
					object.wait(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(exception != null)
				throw exception;

			ba.flush();
			return ba.toByteArray();

		} catch (NullPointerException e) {
			throw new SerialException("找不到串口。");
		} catch (IOException e) {
			throw new SerialException("发送信息到串口时发生IO异常");
		}
	}

	/**
	 * 发送指令，指定时间内不返回则退出等待
	 * @param data
	 * @param milliseconds
	 * @return
	 * @throws SerialException
	 */

	/**
	 * 发送指令
	 * @param data
	 * @return
	 * @throws SerialException
	 */
	public synchronized byte[] send(byte[] data) throws SerialException {
		try {
			rest();

			outputStream = serialPort.getOutputStream();
			outputStream.write(data);
			outputStream.flush();

			synchronized (object) {
				try {
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(exception != null)
				throw exception;

			ba.flush();
			return ba.toByteArray();

		} catch (NullPointerException e) {
			throw new SerialException("找不到串口。");
		} catch (IOException e) {
			throw new SerialException("发送信息到串口时发生IO异常");
		}
	}

	/**
	 * 发送指令，指定时间内不返回则退出等待
	 * @param data
	 * @param milliseconds
	 * @return
	 * @throws SerialException
	 */
	public synchronized byte[] send(byte[] data,long milliseconds) throws SerialException {
		try {
			rest();

			outputStream = serialPort.getOutputStream();
			outputStream.write(data);
			outputStream.flush();

			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					synchronized (object) {
						try {
							hasRev = true;
							object.notify();
							if(receiveHander != null) {
								receiveHander.received(ba.toByteArray());
							}
							rest();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			} ,milliseconds);

			synchronized (object) {
				try {
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if(exception != null)
				throw exception;

			ba.flush();
			return ba.toByteArray();

		} catch (NullPointerException e) {
			throw new SerialException("找不到串口。");
		} catch (IOException e) {
			throw new SerialException("发送信息到串口时发生IO异常");
		}
	}

	private void rest() {
		/**
		 * 清空缓冲区
		 */
		ba.reset();
		hasRev = false;

	}

	/**
	 * 关闭串口
	 * @throws SerialException
	 */
	public void closeSerialPort() throws SerialException {
		if (serialPort != null) {
			serialPort.notifyOnDataAvailable(false);
			serialPort.removeEventListener();
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					throw new SerialException("关闭输入流时发生IO异常");
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
					outputStream = null;
				} catch (IOException e) {
					throw new SerialException("关闭输出流时发生IO异常");
				}
			}
			serialPort.close();
			serialPort = null;
		}
	}

}
