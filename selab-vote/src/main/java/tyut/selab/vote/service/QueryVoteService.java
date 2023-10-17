package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteRange;
import tyut.selab.vote.domain.po.VoteResultDetails;

import java.util.List;

/**
 * @className: QueryVoteService
 * @author: lizhichao
 * @description: TODO 查询投票
 * @date: 2023/10/13 22:00
 * @version: 1.0
 */
public interface QueryVoteService {

     public List<VoteRange> getForMyReport();
     public List<VoteRange> getForMyHold();
     public List<VoteRange> getForUntreated();
     public List<VoteRange> getForParams();

     /**
      *  通过id 查询投票信息
      * @param voteId
      * @return
      */
     public List<VoteInfo> getVoteInfoById(Long voteId);

     /**
      *  通过id 查询投票细节信息
      * @param voteId
      * @return
      */
     public List<VoteResultDetails> getVoteResultDetailsById(Long voteId);
}
