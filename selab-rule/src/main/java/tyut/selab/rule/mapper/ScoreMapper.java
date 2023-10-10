package tyut.selab.rule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScoreMapper {
    /**
     * 增减分数
     * @param score
     */
    public void addOrReduceScore(Integer score,Long userId);

    /**
     * 该用户需要弹出弹窗
     * @param i
     */
    @Update("update rule_score set operation_status = 1")
    void setUserPop(Integer i);

    /**
     * 该操作是否需要弹出弹窗
     * @param i
     */
    @Update("update rule_log set status = 1")
    void setOperationPop(Integer i);
}
