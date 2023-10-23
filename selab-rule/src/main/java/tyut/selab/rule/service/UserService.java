package tyut.selab.rule.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import tyut.selab.rule.domain.VO.LogPageVO;
import tyut.selab.rule.domain.VO.LogVO;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.domain.entity.Operation;

import java.util.Date;
import java.util.List;

public interface UserService {
    //查看用户的当前分数和与该用户相关的操作
    AjaxResult userInfo(Long userId);

    /**
     * 查询用户当天的增减分情况
     * @param userId
     * @return
     */
    Integer getScoreChangeForDay(Long userId);

    /**
     * 查询用户本月的增减分情况
     * @param userId
     */
    Integer getScoreChangeForMonth(Long userId);

    LogPageVO getLogForDay(Long userId, Integer pageNum, Integer pageSize, Date startTime);

    List<OperationVO> getScoreChangeOperationsForDay(Long userId);

    List<OperationVO> getScoreChangeOperationsForMonth(Long userId);
}
