package tyut.selab.rule.service;

/**
 * 奖惩制度业务层
 */
public interface RuleService {
    public int getStatus(Long userId);
    public void setStatus(Long userId,int ruleStatus);
    public void edit(String text);
    public String getText();
}
