package tyut.selab.vote.service;

/**
 * @author Big_bai on 2022/11/22
 */
public interface IWeightControlService {
    /**
     * 修改权重
     * @return
     */
    int setVoteWeight();

    /**
     * 获取权重
     * @return
     */
    int getVoteWeight();
}
