package com.huaxin.cloud.tms.tray.printer;

import com.huaxin.cloud.tms.tray.printer.config.ParamConfigTcp;
import com.huaxin.cloud.tms.tray.printer.exception.TcpException;
import com.huaxin.cloud.tms.tray.printer.service.RevListener;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * tcp交互类，负责处理数据的发送及接收
 *
 * @author Admin
 */
public class TcpListener extends Thread {

    private Socket socket;

    private RevListener revListener;

    public void setRevListener(RevListener revListener) {
        this.revListener = revListener;
    }

    /**
     * 初始化连接
     *
     * @param paramConfigTcp
     * @throws TcpException
     */
    public void init(ParamConfigTcp paramConfigTcp) throws TcpException {
        //客户端对象 建连接
        try {
            socket = new Socket(paramConfigTcp.getHost(), paramConfigTcp.getPort());
            socket.setKeepAlive(true);

            if (revListener != null) {
                while (true) {
                    try {
                        InputStream in = socket.getInputStream();
                        if (in.available() == 0) {
                            Thread.sleep(500);
                            continue;
                        }
                        byte[] data = new byte[in.available()];
                        IOUtils.readFully(in, data);
                        revListener.onReceive(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (UnknownHostException e) {
            throw new TcpException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new TcpException(e.getMessage());
        }
    }

    /**
     * 同步发送数据,阻塞等待响应结果
     *
     * @param data
     * @throws TcpException
     */
    public synchronized void send(byte[] data) throws TcpException {
        try {
            OutputStream out = socket.getOutputStream();
            out.write(data);
            out.flush();
        } catch (Exception e) {
            throw new TcpException("发送数据异常!");
        }
    }


    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

}
