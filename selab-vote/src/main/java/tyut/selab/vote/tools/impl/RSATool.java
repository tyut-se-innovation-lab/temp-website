package tyut.selab.vote.tools.impl;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tyut.selab.vote.tools.VoteKeyTool;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static tyut.selab.vote.tools.VoteKeyTool.privateKey;
import static tyut.selab.vote.tools.VoteKeyTool.publicKey;

/**
 * RSA算法工具类
 * @Author: Gulu
 * @Date: 2022/11/22 11:52
 */

public class RSATool {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    //对base64编码信息解码
    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }
    //对信息进行base64编码
    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 加密
     * @param data
     * @return 加密数据串
     * @throws Exception
     */
    public static String encrypt(String data){
        return encryptByPublicKey(data,publicKey);
    }

    /**
     * 解密
     * @param data 加密数据
     * @return 解密所得数据
     * @throws Exception
     */
    public static String decrypt(String data){
        return new String(decryptByPrivateKey(data,privateKey));
    }
    /**
     * 用私钥对信息生成数字签名
     * @param data 进行加密数据
     * @param privateKey 私钥
     * @return 数字签名串
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     * @param data 加密数据
     * @param publicKey 公钥
     * @param sign 数字签名
     * @return 校验成功返回true，失败则返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }


    @SneakyThrows
    public static byte[] decryptByPrivateKey(byte[] data, String key){
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = null;
        keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = null;
        privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = null;
        cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 用私钥解密
     *
     * @param data 加密数据
     * @param key 私钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key){
        return decryptByPrivateKey(decryptBASE64(data), key);
    }

    /**
     * 用公钥解密
     *
     * @param data 加密数据
     * @param key 公钥
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(data));
    }

    /**
     * 用公钥加密
     *
     * @param data 加密数据
     * @param key 公钥
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static String encryptByPublicKey(String data, String key){
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = null;
        keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = null;
        publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = null;
        cipher = Cipher.getInstance(keyFactory.getAlgorithm());

        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return encryptBASE64(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 用私钥加密
     *
     * @param data 加密数据
     * @param key 私钥
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(byte[] data, String key) throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    /**
     * 获取私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 获取公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap) throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
        return keyMap;
    }
}
