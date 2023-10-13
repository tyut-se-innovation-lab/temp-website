package tyut.selab.vote.tools;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.mapper.DealVoteMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: TimeDealTool
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/9 15:35
 * @version: 1.0
 */
public class TimeDealTool {

    /**
     *   获取服务器当前时间
     * @return
     * @throws ParseException
     */
    public static String getTime() throws ParseException {
         Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }

    /**
     * 判断当前时间是否在指定时间之后
     * @param date
     * @return
     */
    public static boolean compareTime(Date date){
        Date now = new Date();
        boolean flag = date.after(now);
        return flag;
    }


    /**
     * 进行任何操作之前，判断投票是否结束
     * @param voteDeadTime 截止时间
     * @return true已结束
     */
    public static boolean judgeVoteFinish(Date voteDeadTime){
        Date nowDate = DateUtils.getNowDate();
        if(nowDate.compareTo(voteDeadTime) < 0){
            return true;
        }else{
            return false;
        }
    }
}
