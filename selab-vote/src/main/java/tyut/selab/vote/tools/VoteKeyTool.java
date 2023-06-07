package tyut.selab.vote.tools;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tyut.selab.vote.tools.impl.RSATool;

import java.io.File;
import java.io.FileNotFoundException;
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
        System.out.println("必须包含公钥(publicKey),可以不包含私钥(privateKey)");
        System.out.println("文件的第一行放置公钥，第二行可以放置私钥");
        System.out.println("格式：publicKey: xxxxxxx");
        System.out.println("文件路径传入示例:");
        System.out.println("--keyPath: /xx/xx/xxx");
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
        String keyPath = argsMap.get("--keyPath:");
        if (keyPath == null) {
            System.err.println("未传入密钥文件地址");
            printHelp();
            System.exit(114514);
        }
        try {
            Scanner keyReader = new Scanner(new File(keyPath));
            Map<String, String> keyField = new HashMap<>();
            while (keyReader.hasNext()) {
                String[] keyValueInFile = keyReader.nextLine().split(" ");
                if(keyValueInFile.length<2){
                    continue;
                }
                keyField.put(keyValueInFile[0], keyValueInFile[1]);
            }
            if (keyField.size() == 0) {
                System.err.println("文件为空");
                printHelp();
                System.exit(114514);
            }
            if (keyField.get("publicKey:") == null || keyField.get("publicKey:") .equals("")) {
                System.err.println("该文件中没有publicKey或者格式错误");
                printHelp();
                System.exit(114514);
            }
            publicKey = keyField.get("publicKey:");
            privateKey = keyField.get("privateKey:");
            System.out.println("成功读取到参数，启动成功~！");
        } catch (FileNotFoundException fe) {
            printHelp();
            fe.printStackTrace();
            System.exit(114514);
        }
    }
}
