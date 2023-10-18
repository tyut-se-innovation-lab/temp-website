package tyut.selab.rule.service;

import tyut.selab.rule.domain.DTO.ScoreRequestDTO;

import javax.servlet.http.HttpServletRequest;

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
}
