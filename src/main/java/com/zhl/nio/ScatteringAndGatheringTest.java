package com.zhl.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering：将数据写入到 buffer 时，可以采用 buffer 数组，依次写入 [分散]
 * Gathering：从 buffer 读取数据时，可以采用 buffer 数组，依次读
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws Exception {
        //使用 ServerSocketChannel 和 SocketChannel 网络
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress(2255);

        //绑定socket 并启动
        socketChannel.socket().bind(socketAddress);

        //创建bytebuffer 数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel channel = socketChannel.accept();

        int messageLen = 8;

        while (true) {
            int byteRead = 0;

            while (byteRead < messageLen) {
                long l = channel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead = " + byteRead);
                //看此时的byteBuffers中的位数
                Arrays.asList(byteBuffers).stream()
                        .map(buffer -> "position = " + buffer.position() + ". limit = " + buffer.limit())
                        .forEach(System.out::println);
            }
            //对所有的byteBuffer进行反转
            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.flip());

            //将数据读到控制台
            int byteWrite = 0;

            while (byteWrite < messageLen) {
                long l = channel.write(byteBuffers);
                byteWrite += l;
                System.out.println("byteWrite = " + byteWrite);
            }

            //对所有的buffer进行clear
            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead = " + byteRead + ", byteWrite = " + byteWrite + ", messagelen = " + messageLen);
        }
    }
}
