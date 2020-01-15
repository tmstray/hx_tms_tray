package com.huaxin.cloud.tms.tray.printer.config;

/**
 * 串口连接参数配置
 * @author Admin
 *
 */
public class ParamConfigSerial {

	/**
	 * 串口号
	 * eg: COM1、COM2
	 */
	private String serialNumber;
	/**
	 * 波特率
	 */
	private int baudRate = 9600;
	/**
	 * 校验位
	 * 0 ：无
	 * 1： 奇
	 * 2： 偶
	 * */
	private int checkoutBit;
	/**
	 * 数据位
	 */
	private int dataBit;
	/**
	 * 停止位
	 */
	private int stopBit;

	public ParamConfigSerial() {
	}

	/**
	 * 构造方法
	 *
	 * @param serialNumber 串口号
	 * @param baudRate     波特率
	 * @param checkoutBit  校验位
	 * @param dataBit      数据位
	 * @param stopBit      停止位
	 */
	public ParamConfigSerial(String serialNumber, int baudRate, int checkoutBit, int dataBit, int stopBit) {
		this.serialNumber = serialNumber;
		this.baudRate = baudRate;
		this.checkoutBit = checkoutBit;
		this.dataBit = dataBit;
		this.stopBit = stopBit;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getBaudRate() {
		return baudRate;
	}

	public void setBaudRate(int baudRate) {
		this.baudRate = baudRate;
	}

	public int getCheckoutBit() {
		return checkoutBit;
	}

	public void setCheckoutBit(int checkoutBit) {
		this.checkoutBit = checkoutBit;
	}

	public int getDataBit() {
		return dataBit;
	}

	public void setDataBit(int dataBit) {
		this.dataBit = dataBit;
	}

	public int getStopBit() {
		return stopBit;
	}

	public void setStopBit(int stopBit) {
		this.stopBit = stopBit;
	}


}
