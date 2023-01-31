package com.zhl.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <h1>NIO通过channel实现文件复制</h1>
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel channel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel channel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true) {
            byteBuffer.clear();
            int read = channel01.read(byteBuffer);
            if (-1 == read) {
                break;
            }
            byteBuffer.flip();
            channel02.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
