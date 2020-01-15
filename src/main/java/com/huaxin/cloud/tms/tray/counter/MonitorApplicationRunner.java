package com.huaxin.cloud.tms.tray.counter;

import com.huaxin.cloud.tms.tray.common.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(2)
public class MonitorApplicationRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(MonitorApplicationRunner.class);

    //监听端口
    private static final int PORT = Integer.parseInt(ConfigUtil.getProperties("counter.controller.monitorPort"));

    public static final Map<String, Integer> counterMap = new HashMap<>();

    //有人计数器
    private final String counterIp = ConfigUtil.getProperties("counter.controller.ip");
    private final int baseSpeed = Integer.parseInt(ConfigUtil.getProperties("counter.controller.baseSpeed"));

    public static final String createCode = ConfigUtil.getProperties("printer.controller.url");

    @Override
    public void run(ApplicationArguments args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            //建立服务器的Socket，并设定一个监听的端口PORT
            serverSocket = new ServerSocket(PORT);
            //由于需要进行循环监听，因此获取消息的操作应放在一个while大循环中
            while (true) {
                try {
                    //建立跟客户端的连接
                    socket = serverSocket.accept();
                } catch (Exception e) {
                    System.out.println("建立与客户端的连接出现异常");
                    e.printStackTrace();
                }
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (Exception e) {
            System.out.println("端口被占用");
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                log.error("serverSocket error", e);
            }
        }
    }


    //服务端线程类
//继承Thread类的话，必须重写run方法，在run方法中定义需要执行的任务。
    class ServerThread extends Thread {
        private Socket socket;
        DataInputStream inputStream;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {

                int num = 0;
                long lastTime = 0;
                //解决多个司机贴卡时间为导致计算袋数误差
                boolean startFlag = false;
                while (true) {
                    //接收客户端的消息并打印
                    inputStream = new DataInputStream(socket.getInputStream());
                    byte[] bytes = new byte[1024];
                    int read = inputStream.read(bytes);
                    if (read == 11) {
                        String s = Utils.bytesToHex(Arrays.copyOf(bytes, 11));
                        //设备IP地址
                        String ip = socket.getInetAddress().getHostAddress();
                        if (counterIp.indexOf(ip) != -1) {
//                            System.out.println(Utils.byteToHex(bytes[7]));
                            //计数器 42是DI输入上传的，45是DO输出
                            //将输出的过滤掉，在这里只处理计数功能
                            if (Utils.byteToHex(bytes[1]).indexOf("42") != -1) {
                                //直接数组转二进制字符串
                                //0101 从右往左数为DI1开始，DO也是一样的
                                String s1 = Utils.byteToHex(bytes[7]).replace("0x", "");
                                String s2 = Utils.conver2BinStr(Utils.hexStringToByte(s1));
                                String binstr = String.format("%04d", Integer.parseInt(s2));
                                //遍历解析数据
                                for (int i = 1; i <= binstr.length(); i++) {
                                    String s4 = String.valueOf(binstr.charAt(binstr.length() - i));
                                    Integer status = counterMap.get(ip + i + "status");
                                    if (status != null) {
                                        if (status == 1) {
                                            if ("0".equals(s4)) {
                                                startFlag = true;
                                                lastTime = System.currentTimeMillis();
                                            }
                                            if (startFlag) {
                                                if ("1".equals(s4)) {
                                                    //计算差值是为了处理连包的情况
                                                    int diff = (int) (System.currentTimeMillis() - lastTime);
                                                    System.out.println(diff);
                                                    if (diff > (baseSpeed * 100)) {
                                                        int speed = baseSpeed * 100;
                                                        //整数部分
                                                        int wholeInt = diff / speed;
                                                        //余数部分
                                                        int disInt = diff % speed;
                                                        //如果余数大于基本速度的一半，就+1,否则就保持不变，具体算法，再现场优化
                                                        if (disInt > (baseSpeed * 50)) {
                                                            num = num + wholeInt + 1;
                                                        } else {
                                                            num = num + wholeInt;
                                                        }
                                                    }
                                                }
                                            }


                                        }
                                        System.out.println("num:" + num);
                                        counterMap.put(ip + i + "num", num);
                                        Map map = new HashMap();
                                        map.put("number",num);
                                        HttpUtils.sendPost(createCode,"number="+String.valueOf(num));
                                    }
                                }
                            }
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println("客户端主动断开连接了");
                //e.printStackTrace();
                log.error("error", e);
            }
            //操作结束，关闭socket
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("关闭连接出现异常");
                log.error("socket close error", e);
            }
        }
    }
}
