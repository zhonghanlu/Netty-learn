package com.zhl.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <h1>NIO通过channel读</h1>
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception{
        File file = new File("d:\\file01.txt");
        FileInputStream outputStream =new FileInputStream(file);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer =ByteBuffer.allocate((int)file.length());
        channel.read(buffer);
        outputStream.close();
        System.out.println(new String(buffer.array()));
    }
}
