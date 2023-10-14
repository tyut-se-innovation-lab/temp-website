package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.VoteUser;
import tyut.selab.vote.mapper.VoteResultMapper;
import tyut.selab.vote.service.IShowVoteResultService;

import java.util.List;

/**
 * @className: ShowVoteResultServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 18:14
 * @version: 1.0
 */
public class ShowVoteResultServiceImpl implements IShowVoteResultService {


    @Autowired
    VoteResultMapper voteResultMapper;




    // 判断是否被选中
     public Boolean isChoose(List<VoteResult> voteResults,Long optionId){

         for (VoteResult voteResult:voteResults){
             if(voteResult.getVoteOptionId() == optionId){
                return true;
             }
         }
         return false;
     }
}
