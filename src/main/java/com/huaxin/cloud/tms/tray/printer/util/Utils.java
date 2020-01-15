package com.huaxin.cloud.tms.tray.printer.util;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
/**
 * 工具类，算法及字符操作
 *
 * @author Admin
 *
 */
public class Utils {




	/**
	 * 	电子标签读写器
	 *  UHF电子标签读写器用户手册v2.0
	 * 	获取字节数据的CRC16
	 */

	public static byte[] CRC16(byte[] bytes,boolean swap) {
		  int crc = 0xffff; // initial value
	        int polynomial = 0x8408;// poly value reversed 0x1021;

	        int i, j;
	        for (i = 0; i < bytes.length; i++) {
	            crc ^= ((int) bytes[i] & 0x000000ff);
	            for (j = 0; j < 8; j++) {
	                if ((crc & 0x00000001) != 0) {
	                    crc >>= 1;
	                    crc ^= polynomial;
	                } else {
	                    crc >>= 1;
	                }
	            }
	        }

        byte[] data = new byte[2];
		data[swap ? 0 : 1] = (byte) (crc & 0xFF); // 低位
		data[swap ? 1 : 0] = (byte) ((crc & 0xff00) >> 8); // 高位
		return data;
    }







	/**
	 * 获取字节数据的CRC
	 * @param bytes
	 * @param swap  true表示要交互高低位，返回值低位在前，高位在后；false表示不交换，正常返回高位在前，低位在后
	 * @return
	 */
	public static byte[] getCRC16(byte[] bytes, boolean swap) {
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x0000a001;

		int i, j;
		for (i = 0; i < bytes.length; i++) {
			CRC ^= (int) bytes[i];
			for (j = 0; j < 8; j++) {
				if ((CRC & 0x00000001) == 1) {
					CRC >>= 1;
			CRC ^= POLYNOMIAL;
				} else {
					CRC >>= 1;
				}
			}
		}

		byte[] data = new byte[2];
		data[swap ? 0 : 1] = (byte) (CRC & 0xFF); // 低位
		data[swap ? 1 : 0] = (byte) ((CRC & 0xff00) >> 8); // 高位
		return data;
	}

	/**
	 * 喷码机的获取字节数据的CRC
	 */
	public static  byte[] calCrc(byte[] bytes,boolean swap){
		int CRC = 0x00000000;
		int POLYNOMIAL = 0x00001021;

		int i, j;
		for (i = 0; i < bytes.length; i++) {
			CRC ^= (bytes[i] << 8)& 0xff00;
			for (j = 0; j < 8; j++) {
				if ((CRC & 0x8000) == 1) {
					CRC <<= 1;
					CRC ^= POLYNOMIAL;
				} else {
					CRC <<= 1;
				}
			}
		}

		byte[] data = new byte[2];
		data[swap ? 0 : 1] = (byte) (CRC & 0xff); // 低位
		data[swap ? 1 : 0] = (byte) ((CRC << 8)& 0xff00); // 高位

		System.out.println(CRC);//F0 2F
		return data;

	}








	/**
	 * 返回字节数组的十六进制字符串
	 *
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) {
			buf.append(String.format("0x%s ", String.format("%02x", new Integer(b & 0xff)).toUpperCase()));
		}

		return buf.toString().trim();
	}

	public static String bytesToHexString(byte[] data) {
		StringBuffer sbfPrefx = new StringBuffer();
		StringBuffer sbf = new StringBuffer();
		String str = null;
		boolean prefx0 = true;
		for(byte b : data) {
			str = String.valueOf(b & 0xff);
			if(str.equals("0") && prefx0) {
				sbfPrefx.append("00");
				continue;
			}else {
				prefx0 = false;
				sbf.append(String.format("%02x", new Integer(b & 0xff)));
			}
		}
		return sbf.toString();
	}

	/**
	 * 异或计算
	 *
	 * @param data
	 * @return
	 */
	public static byte XOR(byte[] data) {
		byte r = data[0];
		for (int i = 1, s = data.length; i < s; i++) {
			r ^= data[i];
		}
		return r;
	}

	public static String byteToHex(byte b) {
		return String.format("0x%s", String.format("%02x", new Integer(b & 0xff)).toUpperCase());
	}

	/**
	 * 获取字节高低位
	 * @param b
	 * @param swap true表示要交互高低位，返回值低位在前，高位在后；false表示不交换，正常返回高位在前，低位在后
	 * @return
	 */
	public static byte[] highlow(byte b,boolean swap) {

		byte[] data = new byte[2];
		data[swap ? 0 : 1] = (byte) (b & 0xFF); // 低位
		data[swap ? 1 : 0] =  (byte) ((b & 0xff00) >> 8); // 高位
		return data;
	}


	/**
	 * 开头的0x00会被转换为00占位符
	 * 十六进制字节转成十进制字符串
	 * @return
	 */
	public static String hexToInt(byte[] data) {
		StringBuffer sbfPrefx = new StringBuffer();
		StringBuffer sbf = new StringBuffer();
		String str = null;
		boolean prefx0 = true;
		for(byte b : data) {
			str = String.valueOf(b & 0xff);
			if(str.equals("0") && prefx0) {
				sbfPrefx.append("00");
				continue;
			}else {
				prefx0 = false;
				sbf.append(String.format("%02x", new Integer(b & 0xff)));
			}
		}
		return sbfPrefx.append(Long.parseLong(sbf.toString(), 16)).toString();
	}


	public static byte calculateLRC(byte[] bytes) {
		byte LRC = 0;
		for (int i = 1; i < bytes.length; i++) {
			LRC ^= bytes[i];
		}
		return LRC;
	}

	/**
	 * 计算CRC16校验码
	 *
	 * @param bytes
	 * @return
	 */
	public static String getCRC(byte[] bytes) {
		int CRC = 0x0000ffff;
		int POLYNOMIAL = 0x0000a001;

		int i, j;
		for (i = 0; i < bytes.length; i++) {
			CRC ^= ((int) bytes[i] & 0x000000ff);
			for (j = 0; j < 8; j++) {
				if ((CRC & 0x00000001) != 0) {
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				} else {
					CRC >>= 1;
				}
			}
		}
		return Integer.toHexString(CRC);
	}

	public static void main(String[] args) {
		byte[] bytes = new byte[] {(byte)0x02,(byte)0x30,(byte)0x30,(byte)0x00,(byte)0x08,(byte)0x50,(byte)0x3B,(byte)0x31,(byte)0xD4,(byte)0xBB,(byte)0x09,(byte)0xC5,(byte)0xA3,(byte)0x03,(byte)0x53};
		bytes = Arrays.copyOfRange(bytes, 8, 12);
		System.out.println(bytesToHex(bytes));
		ArrayUtils.reverse(bytes);
		System.out.println(String.format("%012d", Long.valueOf(hexToInt(bytes))));
	}

}
