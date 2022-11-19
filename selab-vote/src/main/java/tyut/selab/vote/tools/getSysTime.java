package tyut.selab.vote.tools;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getSysTime {
    /**
     * 得到当前时间
     * 示例：Tue Nov 15 08:37:40 CST 2022
     * @return Date
     */
    public static Date getNow() {
        Date date = new Date();
        return date;
    }

    /**
     * 获取当前时间
     * 2022-11-15 09:52:46
     * @return String 返回时间格式为 "yyyy-MM-dd HH:mm:ss"
     */
    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(currentTime);
        return dateString;
    }

    /**
     * 将时间字符串转为 Date
     *
     * @param dateString 格式："yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date stringToDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
