package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteRange;

import java.util.List;
import java.util.Map;

/**
 * @className: QueryVoteService
 * @author: lizhichao
 * @description: TODO 查询投票
 * @date: 2023/10/13 22:00
 * @version: 1.0
 */
public interface QueryVoteService {

      List<VoteRange> queryForMyReport();
      List<VoteRange> queryForMyHold();
      List<VoteRange> getForUntreated();
      List<VoteRange> queryForParams(Map<String,Integer> params);
      List<VoteRange> getAllVote();

     /**
      *  通过id 查询投票信息
      * @param voteId
      * @return
      */
      VoteInfo getVoteInfoById(Long voteId);

     /**
      *  通过id 查询投票细节信息
      * @param voteId
      * @return
      */
}
