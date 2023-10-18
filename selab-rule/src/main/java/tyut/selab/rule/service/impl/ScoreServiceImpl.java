package tyut.selab.rule.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.DTO.ScoreRequestDTO;
import tyut.selab.rule.mapper.ScoreMapper;
import tyut.selab.rule.service.ScoreService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 分数管理
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private TokenService tokenService;

    /**
     * 增减分数
     *
     * @param scoreRequestDTO
     */
    @Override
    public void addOrReduceScore(HttpServletRequest request, ScoreRequestDTO scoreRequestDTO) {
        scoreRequestDTO.setScoreChange(scoreRequestDTO.getScoreChange() == null ? 0 : scoreRequestDTO.getScoreChange());
        LoginUser user = tokenService.getLoginUser(request);
        Long menderId = user.getUserId();//操作人
        Integer score = scoreMapper.getByUserId(scoreRequestDTO.getUserId());
        score += scoreRequestDTO.getScoreChange();
        scoreMapper.updateScore(score, scoreRequestDTO.getUserId());
        scoreRequestDTO.setMenderId(menderId);
        scoreRequestDTO.setCreateTime(LocalDateTime.now());
        scoreMapper.addScoreLog(scoreRequestDTO);
    }

    /**
     * 该用户需要弹出弹窗
     *
     * @param i
     */
    @Override
    public void setUserPop(Integer i) {
        scoreMapper.setUserPop(1);
    }

    /**
     * 该操作需要弹出弹窗
     *
     * @param i
     */
    @Override
    public void setOperationPop(Integer i) {
        scoreMapper.setOperationPop(1);
    }
}
