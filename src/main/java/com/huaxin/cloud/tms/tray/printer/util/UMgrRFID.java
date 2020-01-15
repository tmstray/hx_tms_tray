package com.huaxin.cloud.tms.tray.printer.util;


import com.huaxin.cloud.tms.tray.printer.util.Result;
import com.huaxin.cloud.tms.tray.printer.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 电子标签读写器
 * UHF电子标签读写器用户手册v2.0
 *
 * @author Admin
 */
public class UMgrRFID {

    private static final byte UNKNOW_COMMAND = 0x00;

    /**
     * 组装命令:
     * Len		Adr		Cmd		Data[AdrTID	LenTID]		CRC-16
     * 0xXX	0xXX	0x01		 0xXX	0xXX		LSB	MSB
     * 常量		变量		常量			  变量		变量			Utils.getCRC16(os.toByteArray(), true)
     * Len	Adr	Cmd	Data[]	LSB-CRC16	MSB-CRC16
     * 数据各部分说明如下：
     * 长度(字节)		说明
     * Len			1		命令数据块的长度，但不包括Len本身。即数据块的长度等于4加Data[]的长度。Len允许的最大值为96，最小值为4。
     * Adr			1		读写器地址。地址范围：0x00~0xFE，0xFF为广播地址，读写器只响应和自身地址相同及地址为0xFF的命令。读写器出厂时地址为0x00。
     * Cmd			1		命令代码。
     * Data[]		不定		参数域。在实际命令中，可以不存在。
     * LSB-CRC16	1		CRC16低字节。CRC16是从Len到Data[]的CRC16值
     * MSB-CRC16	1		CRC16高字节。
     *
     * @param adr
     * @param cmd
     * @param data
     * @return
     */
    public static byte[] createCommand(byte adr, byte cmd, byte[] data) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os.write(4 + (data == null ? 0 : data.length));
            os.write(adr);
            os.write(cmd);
            if (data != null)
                os.write(data);
            os.write(Utils.CRC16(os.toByteArray(), true));
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] responseData(byte adr, byte reCmd, byte status, byte[] data) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os.write(5 + (data == null ? 0 : data.length));
            os.write(adr);
            os.write(reCmd);
            os.write(status);
            if (data != null)
                os.write(data);
            os.write(Utils.CRC16(os.toByteArray(), true));
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 返回值处理
     * 读写器响应数据块
     * Len	Adr	reCmd	Status	Data[]	LSB-CRC16	MSB-CRC16
     * 数据各部分说明如下：
     * 长度(字节)	说明
     * Len			1		响应数据块的长度，但不包括Len本身。即数据块的长度等于5加Data[]的长度。
     * Adr			1		读写器地址。
     * reCmd		1		指示该响应数据块是哪个命令的应答。如果是对不可识别的命令的应答，则reCmd为0x00。
     * Status		1		命令执行结果状态值。
     * Data[]		不定		数据域，可以不存在。
     * LSB-CRC16	1		CRC16低字节。CRC16是从Len到Data[]的CRC16值。
     * MSB-CRC16	1		CRC16高字节。
     *
     * @param data
     * @throws Exception
     */
    public static Result solveRevData(byte[] data) throws Exception {
        System.out.println("卡号为:" + Utils.bytesToHex(data));
        if (data == null || data.length < 6) {
            throw new Exception("返回值格式错误！");
        }

        if (data[2] == UNKNOW_COMMAND) {
            throw new Exception("不可识别命令！");
        }

        byte[] crc = Utils.CRC16(Arrays.copyOf(data, data.length - 2), true);

        if (crc[0] == data[data.length - 2] && crc[1] == data[data.length - 1]) {
            Result result = new Result();
            result.setAdr(data[2]);
            result.setStatus(data[3]);
            if (data.length == 6)
                result.setData(null);
            else
                result.setData(Arrays.copyOfRange(data, 4, data.length - 2));
            return result;
        }
        throw new Exception("返回值CRC验证异常！");
    }


}
