package tyut.selab.vote.tools;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 权重的工具类
 * @author Big_bai on 2022/11/22
 */
public class WeightControl {
    /**
     * 目前使用的权重在数据库中的id，使用配置文件保存
     */
    private static Long weight;

    private static final String WEIGHTFILE = "../../resources/weightFile.txt";

    //用于初始化读取权重值
    static {
        try (Scanner sc = new Scanner(new FileReader(WEIGHTFILE));){
            weight = sc.nextLong();
            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e+"初始化权重失败");
        }
    }
    /**
     * 获取权重
     * @return 当前权重值
     */
    public static long getWeight() {
        return weight;
    }

    /**
     * 修改使用的权重
     * @param weightId 新的权重在数据库的id
     * @return 存文件是否成功
     */
    public static int setWeight(long weightId){
        weight = weightId;
        return writeWeightInFile(weight);
    }

    /**
     * 写入文件
     * @param weightId 新的权重在数据库中的id
     * @return 是否存入成功
     */
    private static int writeWeightInFile(long weightId){
        try(FileWriter fileWriter = new FileWriter(new File(WEIGHTFILE));){
            fileWriter.write(String.valueOf(weight));
        }catch(IOException e){
            throw new RuntimeException(e + "权重写入文件失败");
        }
        return 1;
    }


}
