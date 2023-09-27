package tyut.selab.rule.mapper;

import org.apache.ibatis.annotations.Param;


public interface RuleMapper {
    public int getStatus(@Param("user_id") Long userId);
    public void setStatus(@Param("user_id") Long userId,@Param("rule_statusp") int ruleStatus);
    public void edit(@Param("text") String text);
    public String getText();
}
