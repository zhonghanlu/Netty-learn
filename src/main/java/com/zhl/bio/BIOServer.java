package com.zhl.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class BIOServer {

    public static void main(String[] args) throws Exception {
        //线程池机制
        //思路
        //1. 创建一个线程池
        //2. 如果有客户端连接，就创建一个线程，与之通讯(单独写一个方法)
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        log.info("服务器启动了");
        while (true) {
            log.info("线程id{}，线程名字{}", Thread.currentThread().getId(), Thread.currentThread().getName());
            //监听等待客户端连接
            log.info("等待连接");
            //阻塞在accept
            final Socket socket = serverSocket.accept();
            log.info("连接到一个客户端");
            //创建线程与之通信
            newCachedThreadPool.execute(() -> hadler(socket));
        }

    }

    //与客户端进行通信
    private static void hadler(Socket socket) {
        log.info("线程id{}，线程名字{}", Thread.currentThread().getId(), Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        //通过socket获得输入流
        try {
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端的消息
            while (true) {
                log.info("线程id{}，线程名字{}", Thread.currentThread().getId(), Thread.currentThread().getName());
                log.info("read......");
                int read = inputStream.read(bytes);
                if (read != -1) log.info(new String(bytes, 0, read));
                else
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
