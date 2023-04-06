package tyut.selab.attendance.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Gulu
 * @Date: 2023/3/27 16:16
 */
@Mapper
public interface GenerateLogMapper {
    /**
     * 删除数据库中的本周信息(清空)
     */
    public void deleteThisWeekLog();

    /**
     * 删除数据库中的本周统计信息(清空)
     */
    public void deleteThisWeekLogStatistics();

    /**
     * 删除数据库中未签退数据
     */
    public void deleteInvalidData();
}
