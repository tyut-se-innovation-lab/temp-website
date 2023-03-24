package tyut.selab.attendance.service;

/**
 * 创建日志
 * 每周末定时更新日志
 */
public interface IGenerateLogService {
    /**
     * 生成本周的日志文件
     */
    void writeLogFileThisWeek();

    /**
     * 删除数据库中的本周信息
     */
    void deleteThisWeekLog();
}
