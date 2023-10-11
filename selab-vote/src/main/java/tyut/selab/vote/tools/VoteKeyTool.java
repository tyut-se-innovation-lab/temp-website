package tyut.selab.vote.tools;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tyut.selab.vote.tools.impl.RSATool;

import java.io.*;
import java.util.*;

/**
 * 验证输入并从控制台获取密钥
 *
 * @author Big-Bai
 **/
@Component
public class VoteKeyTool {
    /**
     * 公钥
     */
    public static String publicKey;
    /**
     * 私钥
     */
    public static String privateKey;


    private static void printHelp() {
        System.out.println("====================================================");
        System.out.println("本系统 投票模块 需要在启动时传入存放密钥的文件的绝对路径,不需要包含扩展名");
        System.out.println("公钥私钥请满足RSA算法要求");
        System.out.println("文件编写要求：");
        System.out.println("文件包含完全体公钥");
        System.out.println("文件路径传入示例:");
        System.out.println("--keyPath /xx/xx/xxx");
        System.out.println("请注意-- 与大小写");
        System.out.println("====================================================");

    }

    public static void run(String... args) {
        if (args.length < 1) {
            printHelp();
            System.exit(10086);
        }
        Map<String, String> argsMap = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if (i % 2 == 0) {
                argsMap.put(args[i], args[i + 1]);
            }
        }
        parseKeyPath(argsMap);
    }

    public static void parseKeyPath(Map<String, String> argsMap) {
        String keyPath = argsMap.get("--keyPath");
        if (keyPath == null) {
            System.err.println("未传入密钥文件地址");
            printHelp();
            System.exit(114514);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(keyPath));
            String s = br.readLine();
            StringBuilder builder = new StringBuilder();
            s = br.readLine();
            while (s.charAt(0) != '-') {
                builder.append(s);
                s = br.readLine();
            }
            publicKey = builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
