package com.huaxin.cloud.tms.tray.printer.config;

/**
 * tcp协议配置
 * @author Admin
 *
 */
public class ParamConfigTcp {

	/**
	 * 主机
	 */
	private String host;

	/**
	 * 端口号
	 */
	private int port;

	public ParamConfigTcp() {
	}

	public ParamConfigTcp(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


}
