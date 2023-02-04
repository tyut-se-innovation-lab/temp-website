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
    public static String encrypt(Long data){
        return RSATool.encrypt(data.toString());
    }

    /**
     * 解密
     * @param data 加密数据
     * @return 解密所得数据
     * @throws Exception
     */
    public static Long decrypt(String data){
        return Long.parseLong(RSATool.decrypt(data));
    }

    public static void main(String[] args) {
        try {
            String encrypt = encrypt(7787L);
            System.out.println(encrypt);
            System.out.println(decrypt(encrypt));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
