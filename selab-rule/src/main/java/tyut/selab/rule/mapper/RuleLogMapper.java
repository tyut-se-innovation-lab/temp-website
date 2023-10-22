package tyut.selab.rule.mapper;

import tyut.selab.rule.domain.RuleLog;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.domain.entity.Operation;

import java.util.Date;
import java.util.List;

/**
 * @author Tapper
 * @description 针对表【rule_log(奖惩制度的日志记载)】的数据库操作Mapper
 * @createDate 2023-10-09 12:03:15
 * @Entity tyut.selab.rule.domain.RuleLog
 */
public interface RuleLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RuleLog record);

    int insertSelective(RuleLog record);

    List<RuleLog> selectByUserId(Long userId);

    int updateByPrimaryKeySelective(RuleLog record);

    int updateByPrimaryKey(RuleLog record);

    int deleteByLogId(Long logId);

    int getByRuleLogId(Long logId);
    List<Operation> selectAllLog();
    List<Operation> getLogForDay(Long userId, Date startTime, Date endTime);
}
