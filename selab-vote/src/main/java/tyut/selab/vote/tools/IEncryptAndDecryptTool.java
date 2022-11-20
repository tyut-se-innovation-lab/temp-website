package tyut.selab.vote.tools;

/**
 * 加密解密
 * @author Big_bai on 2022/11/20
 */
public interface IEncryptAndDecryptTool {
    /**
     * 加密
     * @param obj 传入任意对象
     * @return 加密好的对象
     */
    public Object encrypt(Object obj);

    /**
     * 解密
     * @param obj 待解密的对象
     * @return 解密好的对象
     */
    public Object decrypt(Object obj);

    /**
     * 从控制台获取密钥
     * @param key
     */
    public void getData(String key);

}
