package tyut.selab.rule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tyut.selab.rule.domain.entity.Operation;

import java.util.List;

@Mapper
public interface RuleMapper {
    public int getRuleStatus(@Param("user_id") Long userId);

    public void setRuleStatus(@Param("user_id") Long userId, @Param("rule_status") int ruleStatus);

    @Select("select operation_status from rule_score where user_id = #{userId}")
    Integer getUserStatus(Long userId);

    @Select("select status from rule_log where target_user_id = #{userId}")
    Integer getOperationStatus(Long userId);

    List<Operation> getOperationInfo(Long userId);

    void clearAllScore();
}
