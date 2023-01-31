package com.zhl.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * <h1>channel的拷贝方法</h1>
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("d:\\a1.png");
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\a2.png");

        FileChannel channel01 = fileInputStream.getChannel();
        FileChannel channel02 = fileOutputStream.getChannel();

        channel02.transferFrom(channel01,0,channel01.size());

        fileInputStream.close();
        fileOutputStream.close();
    }
}
