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
    int setVoteWeight(Weight w);

    /**
     * 获取权重
     * @return
     */
    Weight getNowVoteWeight();
}
