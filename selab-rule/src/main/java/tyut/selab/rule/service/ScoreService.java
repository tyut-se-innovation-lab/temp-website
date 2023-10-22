package tyut.selab.rule.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import tyut.selab.rule.domain.DTO.ScoreRequestDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 分数管理业务层
 */
public interface ScoreService {
    /**
     * 分数增减
     *
     * @param scoreRequestDTO
     */
    void addOrReduceScore(HttpServletRequest request, ScoreRequestDTO scoreRequestDTO);

    /**
     * 该用户需要弹出弹窗
     *
     * @param i
     */
    void setUserPop(Integer i);

    /**
     * 该操作需要弹出弹窗
     *
     * @param i
     */
    void setOperationPop(Integer i);

    List<SysUser> selectUserList(SysUser user, Long scoreCheck);
}
