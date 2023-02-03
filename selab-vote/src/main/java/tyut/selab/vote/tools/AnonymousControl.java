package tyut.selab.vote.tools;

import tyut.selab.vote.tools.impl.RSATool;

/**
 * @Author: Gulu
 * @Date: 2022/11/22 22:10
 */
public class AnonymousControl {
    /**
     * 加密
     * @param data 要加密数据
     * @return 加密数据串
     * @throws Exception
     */
    public static Long encrypt(Long data) throws Exception {
        return longFrom8Bytes(RSATool.encrypt(data.toString()),0,false);
    }

    /**
     * 解密
     * @param data 加密数据
     * @return 解密所得数据
     * @throws Exception
     */
    public static Long decrypt(Long data) throws Exception {
        return longFrom8Bytes(RSATool.decrypt(RSATool.decryptBASE64(data.toString())),0,false);
    }

    public static long longFrom8Bytes(byte[] input, int offset, boolean littleEndian){
        long value=0;
        // 循环读取每个字节通过移位运算完成long的8个字节拼装
        for(int  count=0;count<8;++count){
            int shift=(littleEndian?count:(7-count))<<3;
            value |=((long)0xff<< shift) & ((long)input[offset+count] << shift);
        }
        return value;
    }

    public static void main(String[] args) {
        try {
            Long hhh = encrypt(7787L);
            System.out.println(encrypt(7787L));
            System.out.println(decrypt(hhh));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
