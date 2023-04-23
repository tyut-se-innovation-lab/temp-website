package tyut.selab.vote.service;

import java.util.Map;

/**
 * @author Big_bai on 2022/11/22
 */
public interface IWeightControlService {
    /**
     * 修改权重
     */
    void setVoteWeight(Map<String,Integer> mapRoleWeight);

    /**
     * 获取权重列表
     * @return 身份名和权重数值的映射
     */
    Map<String,Integer> getNowVoteWeight();

    /**
     * 通过用户id获取该用户的权重
     * @return 权限数值
     */
    int getWeightByUserId(String id);

    Long getLastUseWeightId();
}
