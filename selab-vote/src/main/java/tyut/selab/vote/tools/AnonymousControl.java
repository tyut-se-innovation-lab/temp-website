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
    public static String encrypt(String data){
        return RSATool.encrypt(data);
    }

    /**
     * 解密
     * @param data 加密数据
     * @return 解密所得数据
     * @throws Exception
     */
    public static String decrypt(String data){
        return RSATool.decrypt(data);
    }

    public static void main(String[] args) {
        try {
            String encrypt = encrypt("gfjhdj");
            System.out.println(encrypt);
            System.out.println(decrypt(encrypt));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
