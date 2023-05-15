package tyut.selab.vote.tools;

import tyut.selab.vote.tools.impl.RSATool;

/**
 * 验证输入并从控制台获取密钥
 * @author Big-Bai
 **/
public class VoteKeyTool {
    /**
     * 公钥
     */
    public static String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAM2RbjxXkS2hqdm/gES9FBmxxknA39o6RAFACwIl9KnluJdkpEPVSnVuUWZbxESYi76TMl0oX4VstgcSQ5Bg2BkCAwEAAQ==";
    /**
     * 私钥
     */
    public static String privateKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAzZFuPFeRLaGp2b+ARL0UGbHGScDf2jpEAUALAiX0qeW4l2SkQ9VKdW5RZlvERJiLvpMyXShfhWy2BxJDkGDYGQIDAQABAkAFvxL9zNTaGbLNy3Sid0agkT+hwCqBxy8vecLVBIqaVSzBthmGCyV/7hif6MbjqPqiQ6pc9c+UaixLuuybfjwhAiEA9J7P1Fjvo8Mu1+uUcRkVD+vSp+nH+z0G6x5KysOoL4cCIQDXIYtquiyzrmRJsD39ltnM+h3MUdCqZlYK7D3sKjpjXwIgXAejiPAsuB+mb/VhU2tsUSEXj7SUWCENiUdMPByAGeECIF/WpdFqFzgE3NCnHza12Vmjo1q+qAMBjBtZm0x2k4RpAiAV/V7WiA1kv1R6062u7aCzEzdT9MWwgKawaMIaOvyB2A==";

    /**
     * 获取密钥
     * @param args
     * @return
     */
    public static boolean isKeyPassed(String[] args){
        if(args.length<2){
            System.out.println("需要传入两个参数");
            printHelp();
            return false;
        }

        String[] isPublic = args[0].split("publicKey:");
        String[] isPrivate = args[1].split("privateKey:");
        if(isPublic.length<2||isPrivate.length<2){
            System.out.println("某一个参数名称错误或未传值");
            printHelp();
            return false;
        }
        publicKey = isPublic[1];
        privateKey = isPrivate[1];

        String testString = "testString-I love you";
        String encrypted = RSATool.encrypt(testString);
        String decrypted = RSATool.decrypt(encrypted);
        if(!testString.equals(decrypted)){
            System.out.println("公钥私钥不匹配,请重新输入");
            printHelp();
            return false;
        }
        System.out.println("已获取投票参数+"+publicKey+" "+privateKey);
        return true;
    }

    private static void printHelp(){
        System.out.println("====================================================");
        System.out.println("本系统 投票模块 需要在启动时传入用于加密信息的密钥(公钥与私钥)");
        System.out.println("公钥私钥请满足RSA算法要求");
        System.out.println("传入格式如下:");
        System.out.println("--publicKey:xxx --privateKey:xxx");
        System.out.println("示例：");
        System.out.println("java -jar ruoyi-admin.jar --publicKey:xxx --privateKey:xxx");
        System.out.println("请注意空格与--还有大小写");
        System.out.println("====================================================");

    }
}
