package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Weight;

import java.util.List;

/**
 * 查找信息
 * @author Big_bai on 2022/11/22
 */
public interface FindInfoDBMapper {
    /**
     * 根据id查询投票信息
     * @param id
     * @return
     */
    public List<VoteInfo> findVoteInfoById(long id);

    public int updateVoteInfoById(String status,long id);
}
