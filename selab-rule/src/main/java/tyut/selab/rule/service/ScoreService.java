package tyut.selab.rule.service;

/**
 * 分数管理业务层
 */
public interface ScoreService {
    /**
     * 分数增减
     * @param score
     */
    void addOrReduceScore(Integer score,Long userId);

    /**
     * 该用户需要弹出弹窗
     * @param i
     */
    void setUserPop(Integer i);

    /**
     * 该操作需要弹出弹窗
     * @param i
     */
    void setOperationPop(Integer i);
}
