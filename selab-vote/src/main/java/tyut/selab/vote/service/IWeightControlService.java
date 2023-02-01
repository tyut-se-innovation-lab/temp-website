package tyut.selab.vote.service;

import tyut.selab.vote.domain.vo.Weight;

/**
 * @author Big_bai on 2022/11/22
 */
public interface IWeightControlService {
    /**
     * 修改权重
     * @return
     */
    void setVoteWeight(Weight w);

    /**
     * 获取权重列表
     * @return
     */
    Weight getNowVoteWeight();

    /**
     * 通过用户id获取该用户的权重
     * @return
     */
    int getWeightByUserId(Long id);
}
