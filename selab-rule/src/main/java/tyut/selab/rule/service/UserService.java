package tyut.selab.rule.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;

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
}
