package com.zhl.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

@Slf4j
public class BasicBuffer {

    public static void main(String[] args) {
        //举例说明 Buffer 的使用(简单说明)
        //创建一个 Buffer，大小为 5，即可以存放 5 个 int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        //如何从 buffer 读取数据
        //将 buffer 转换，读写切换(!!!)
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            log.info(String.valueOf(intBuffer.get()));
        }

    }

}
