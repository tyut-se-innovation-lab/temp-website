package tyut.selab.rule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tyut.selab.rule.domain.RuleScore;
import tyut.selab.rule.domain.VO.LogVO;
import tyut.selab.rule.domain.VO.OperationVO;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author Tapper
* @description 针对表【rule_score(人员分数)】的数据库操作Mapper
* @createDate 2023-10-09 12:03:15
* @Entity tyut.selab.rule.domain.RuleScore
*/
@Mapper
public interface RuleScoreMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RuleScore record);

    int insertSelective(RuleScore record);

    List<RuleScore> selectByUserId(Long userId);

    int updateByPrimaryKeySelective(RuleScore record);

    int updateByPrimaryKey(RuleScore record);

    /**
     * 根据时间查询分数增减情况
     * @param begin
     * @param end
     * @return
     */
    Integer getScoreChangeByTime(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("userId") Long userId);


    List<OperationVO> getScoreChangeOperationsByTime(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end, @Param("userId") Long userId);

}
