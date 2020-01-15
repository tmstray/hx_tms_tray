package com.huaxin.cloud.tms.tray.printer;

import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.printer.config.ParamConfigSerial;
import com.huaxin.cloud.tms.tray.printer.config.ParamConfigTcp;
import com.huaxin.cloud.tms.tray.printer.exception.SerialException;
import com.huaxin.cloud.tms.tray.printer.exception.TcpException;
import com.huaxin.cloud.tms.tray.printer.util.Utils;

import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Service
public class DWPrintTest {


    @Test
    public void serialPort() throws Exception {
        SerialPortListener listener;
        //实例化串口配置
        ParamConfigSerial pc = new ParamConfigSerial();
        //设置波特率
        pc.setBaudRate(9600);
        //设置端口号
        pc.setSerialNumber("COM6");
        //设置校验位
        pc.setCheckoutBit(0);
        //设置数据位
        pc.setDataBit(8);
        //设置停止位
        pc.setStopBit(1);
        listener = new SerialPortListener();
        listener.init(pc);
        listener.start();
        //加载串口配置
        //串口连接
        connectionHandshake(listener);
        // 发送测试指令
        writeCommand(listener, "asfdsadfsadf".getBytes());
        try {
            listener.closeSerialPort();
        } catch (SerialException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测到打印机 #1的连接
     *
     * @return
     * @throws Exception
     */
    public void connectionHandshake(SerialPortListener listener) throws Exception {
        //0x1B：指令开始标识 ，0x41：喷码机地址，0x6：指令编号，0x0d：指令结束标志 获取喷码机版本
        byte[] param = new byte[]{0x1B, 0x41, 0x6E, 0x21, 0x0D};
        //接受响应字节数组
        byte[] respon = listener.send(param);
        //翻译数据
        System.out.println(String.format("握手检测连接发送:%s,返回:%s", Utils.bytesToHex(param), Utils.bytesToHex(respon)));
    }

    public void writeCommand(SerialPortListener listener, byte[] data) throws IOException, SerialException {
        byte[] head = {0x1B, 0x41, 0x29};
        byte length = (byte) (34 + data.length);
        System.out.println(Utils.bytesToHex(new byte[]{length}));
        byte body = 0x20;
        byte[] footer = {0x0D};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(head);
        os.write(length);
        os.write(body);
        os.write(data);
        os.write(footer);
        listener.send(os.toByteArray());
        System.out.println(Utils.bytesToHex(os.toByteArray()));
    }

    @Test
    public void tcpPrintTest() throws TcpException, IOException {
        TcpListener listener = new TcpListener();
        String printCode="";
        ParamConfigTcp paramConfigTcp = new ParamConfigTcp();
        //设置TCP地址
//        paramConfigTcp.setHost("192.168.10.11");
        paramConfigTcp.setHost("192.168.0.7");
        //设置TCP端口号
//        paramConfigTcp.setPort(1024);
        paramConfigTcp.setPort(8808);
        if(StringUtils.isEmpty(printCode)){
            printCode="12222222222000001";
        }

        //加载TCP配置
        listener.init(paramConfigTcp);
        writeCommand(listener,printCode.getBytes("GB2312"));

    }

    public void writeCommand(TcpListener tcpListener, byte[] data) throws IOException, TcpException {
        byte[] head = {0x1B, 0x41, 0x29};
        byte length = (byte) (34 + data.length);
        byte body = 0x20;
        byte[] footer = {0x0D};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(head);
        os.write(length);
        os.write(body);
        os.write(data);
        os.write(footer);
        tcpListener.send(os.toByteArray());
        System.out.println(String.format("数据发送:%s", Utils.bytesToHex(os.toByteArray())));
    }

    @Test
    public void testFont() throws UnsupportedEncodingException {
        String font ="1";
        System.out.println(Utils.bytesToHex(font.getBytes()));
    }
}
