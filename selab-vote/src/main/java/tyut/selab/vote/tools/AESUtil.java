package tyut.selab.vote.tools;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


/**
 *
 * @className: AESUtil
 * @package: com.soft.team.base.encryption
 * @author: yuanzf
 * @date: 2022/3/16 16:41
 */
public class AESUtil {
    private static String key;
//    @Value("${parseKey.path}")
    private static String parsePath = "C:\\Users\\26580\\Documents\\Fax\\key.txt";


    /**
     *
     * home.php?mod=space&uid=952169 str 加密前数据
     * home.php?mod=space&uid=155549 返回加密后数据
     */
    public static String encrypt(String str){
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.encryptBase64(str);
    }

    /**
     *
     * @param str 加密后的数据
     * @return 返回解密后数据
     */
    public static String decrypt (String str){
        AES aes = SecureUtil.aes(key.getBytes());
        return aes.decryptStr(str);
    }
    static {
        try {
            BufferedReader br = new BufferedReader(new FileReader(parsePath));
            String s = br.readLine();
            StringBuilder builder = new StringBuilder();
            while (s.charAt(0) != '-') {
                builder.append(s);
                s = br.readLine();
            }
           key = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
