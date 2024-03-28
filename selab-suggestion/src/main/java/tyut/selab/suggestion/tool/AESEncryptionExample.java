package tyut.selab.suggestion.tool;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * @ClassName: AESEncryptionExample
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:57
 * @Version: 1.0
 **/

public class AESEncryptionExample {

    private static final String SECRET_KEY = "mysecretkey1234321315"; // 16字节的密钥

    public static String encrypt(String data,String key1) throws Exception {
        key1= adjustString(key1);
        SecretKeySpec key = new SecretKeySpec(key1.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.encodeBase64String(encryptedBytes);
    }

    public static String decrypt(String encryptedData,String key1) throws Exception {
//        key1= Encrypt(key1);
        key1= adjustString(key1);
        SecretKeySpec key = new SecretKeySpec(key1.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encryptedData));
        return new String(decryptedBytes);
    }
    public static String Encrypt(String Password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(Password.getBytes("utf-8"));
            byte[] digest = md.digest();
            return String.valueOf(Hex.encodeHex(digest));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
//        String originalData = "Hello, this is a secret message!";
//        String key = "1234567890123456";
//        String encryptedData = encrypt(originalData,key);
//        System.out.println("Encrypted data: " + encryptedData);
//        String decryptedData = decrypt(encryptedData,key);
//        System.out.println("Decrypted data: " + decryptedData);
        System.out.println(Encrypt("admin"));
        String input1 = "12345678901234567";
        String adjusted1 = adjustString(input1);
        System.out.println(adjusted1); // Output: 1234567890123456

        String input2 = "12345";
        String adjusted2 = adjustString(input2);
        System.out.println(adjusted2); // Output: 1234500000000000
    }
    public static String adjustString(String input) {
        if (input.length() >= 16) {
            return input.substring(0, 16);
        } else {
            StringBuilder sb = new StringBuilder(input);
            while (sb.length() < 16) {
                sb.append("*");
            }
            return sb.toString();
        }
    }

}
