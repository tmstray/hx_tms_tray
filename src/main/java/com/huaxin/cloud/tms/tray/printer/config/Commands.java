package com.huaxin.cloud.tms.tray.printer.config;

public class Commands {

	/**
	 * 查询标签
	 */

	public static final byte SELECT_STATUS = 0x01;

	/**
	 * 8.4.1 读取读写器信息
	 */
	public static final byte READ_INFO_CMD = 0x21;
	public static final byte READ_INFO_STATUS = 0x00;

	/**
	 * 8.4.2 设置读写器工作频率
	 */
	public static final byte SET_FRE_CMD = 0x22;

	/**
	 * 8.4.3 设置读写器地址
	 */
	public static final byte SET_ADR_CMD = 0x24;

	/**
	 * 8.4.4 设置读写器询查时间
	 */
	public static final byte SET_QUERYTIME_CMD = 0x25;

	/**
	 * 8.4.5 设置串口波特率Set the serial port baud rate
	 */
	public static final byte SET_BAUDRATE_CMD = 0x28;

	/**
	 * 8.4.6 调整功率modulating power
	 */
	public static final byte MODULPWR_CMD = 0x2F;

	/**
	 * 8.4.7 声光控制命令silentT
	 */
	public static final byte SILENTT_CMD = 0x33;

	/**
	 * 8.4.8 韦根参数设置命令Wigan parameter setting command
	 */
	public static final byte WIGANPA_CMD = 0x34;

	/**
	 * 8.4.9 工作模式设置命令Working mode Settings command
	 */
	public static final byte SET_PARAMETER_CMD = 0x35;

	/**
	 * 8.4.10 读取工作模式参数Read working mode parameters
	 */
	public static final byte READ_WMP_CMD = 0x36;

	/**
	 * 8.4.11 EAS检测精度设置EAS Detection accuracy setting
	 */
	public static final byte SET_EAS_CMD = 0x37;

	/**
	 * 8.4.12 Syris响应偏置时间设置Syris Response bias time setting
	 */
	public static final byte SET_SYRIS_CMD = 0x38;

	/**
	 * 8.4.13 触发延时设置Trigger delay setting
	 */
	public static final byte SET_TEITIME_CMD = 0x3b;


	/**
	 *	 补充: 设置继电器状态
	 */
	public static final byte SET_RELAYSTATUS_CMD = 0x3c;





	// 询查标签
	public static final byte SELECT_STATUS_6C = 0x01;
	// 读取标签
	public static final byte READ_STATUS_6C = 0x02;
	// 写入标签
	public static final byte WRITE_STATUS_6C = 0x03;
	// 写入EPC号
	public static final byte WRITE_EPC_6C = 0x04;
	// 销毁标签
	public static final byte DESTORY_STATUS_6C = 0x05;
	// 设定存储区读写保护状态
	public static final byte SET_MEM_RW_STA_6C = 0x06;
	// 块擦除
	public static final byte BLOCK_ERASE_6C = 0x07;
	// 读保护设置(根据EPC号设定)
	public static final byte READ_PROTECT_SET_6C = 0x08;
	// 读保护设置(不需要EPC)
	public static final byte READ_PROTECT_SET_NOT_EPC_6C = 0x09;
	// 解锁读保护
	public static final byte DELOCK_PROTECT_SET_6C = 0x0a;
	// 测试标签是否被设置读保护
	public static final byte TEST_TAG_PROTECT_SET_6C = 0x0b;
	// EAS报警设置
	public static final byte EAS_WARN_SET_6C = 0x0c;
	// EAS预警检测
	public static final byte EAS_WARN_CHECK_6C = 0x0d;
	// user区块锁
	public static final byte USER_BLOCK_6C = 0x0e;
	// 查询单张标签
	public static final byte SELECT_ONE_TAG_6C = 0x0f;
	// 块写命令
	public static final byte Block_WRITE_6C = 0x10;
	// 寻找命令
	public static final byte FIND_ONE_TAG_6B = 0x50;
	// 按条件查询标签
	public static final byte FIND_TERM_TAG_6B = 0x51;
	// 读数据
	public static final byte READ_DATA_6B = 0x52;
	// 写数据
	public static final byte WRITE_DATA_6B = 0x53;
	// 锁定检测
	public static final byte LOCK_CHECK_6B = 0x54;
	// 锁定
	public static final byte LOCK_6B = 0x55;
}
