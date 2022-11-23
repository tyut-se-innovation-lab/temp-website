package tyut.selab.vote.tools;

import tyut.selab.vote.tools.impl.RSATool;

/**
 * @Author: Gulu
 * @Date: 2022/11/22 22:10
 */
public class anonymousControl {
    /**
     * 加密
     * @param data 要加密数据
     * @return 加密数据串
     * @throws Exception
     */
    public static byte[] encrypt(String data) throws Exception {
        return RSATool.encrypt(data);
    }

    /**
     * 解密
     * @param data 加密数据
     * @return 解密所得数据
     * @throws Exception
     */
    public static String decrypt(byte[] data) throws Exception {
        return RSATool.decrypt(data);
    }
}
