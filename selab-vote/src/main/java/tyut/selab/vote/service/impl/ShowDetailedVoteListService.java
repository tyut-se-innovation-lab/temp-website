package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShowDetailedVoteListService implements IShowDetailedVoteListService {
    @Autowired
    GetInfoDBMapper mapper;
    @Autowired
    WeightControlService weight;
    /**
     * 获取详细投票信息
     * @param voteId 投票ID
     * @return
     */
    public Questionnaire showDetailedVote(Long voteId){
        VoteInfo voteInfo = mapper.getInfoByVoteId(voteId);
        boolean isDead = !voteInfo.getDeadline().after(new Date());
        Questionnaire info = VoPoConverterTool.info(voteInfo);
        info.setJoin(isJoin(voteId));
        info.setPeoples(mapper.theNumOfJoinVote(voteId));
        List<PoVoteOption> que = mapper.getQueByVoteId(voteId);
        List<VoteQue> voteQues = VoPoConverterTool.que(que);
        improveQue(voteQues,isDead);
        info.setVoteQues(voteQues);
        return info;
    }

    /**
     * 根据是否参加过投票显示百分比
     * @param voteQues
     * @param isJoin
     */
    private void improveQue(List<VoteQue> voteQues, boolean isJoin){
        if(isJoin){
            for (VoteQue voteQue : voteQues) {
                List<PoVoteOption> option = mapper.getOptionByParentId(voteQue.getId());
                for (PoVoteOption poVoteOption : option) {
                    poVoteOption.setPercentage(getPercentage(getVotesByVoteId(poVoteOption.getId()),getTotalVotes(option)));
                }
                List<VoteOption> options = VoPoConverterTool.options(option);
                voteQue.setOptions(options);
            }
        }else {
            for (VoteQue voteQue : voteQues) {
                List<PoVoteOption> option = mapper.getOptionByParentId(voteQue.getId());
                List<VoteOption> options = VoPoConverterTool.options(option);
                voteQue.setOptions(options);
            }
        }

    }

    /**
     * 判断当前用户是否参加过该投票
     * @param voteId
     * @return
     */
    public Boolean isJoin(Long voteId){
        int num = mapper.getJoinedVoteNum(String.valueOf(getUserId()), voteId);
        if(num != 0){
            return true;
        }return false;
    }
    /**
     * 获取某个问题全部的票数
     * @param optionId
     * @return
     */
    private Long getTotalVotes(List<PoVoteOption>optionId){
        Long totalVote = 0L;
        List<VoteResult> results = mapper.getWeightByOptions(optionId);
        for (VoteResult result : results) {
            totalVote += result.getWeight();
        }
        return totalVote;
    }
    /**
     * 获取某个选项获得的票数
     * @param optionId
     * @return
     */
    private Long getVotesByVoteId(long optionId){
        Long num = 0L;
        List<VoteResult> results = mapper.getWeightByOption(optionId);
        for (VoteResult result : results) {
            num += result.getWeight();
        }
        return num;
    }
    /**
     * 获取百分比
     * @param optionNum
     * @param allVoteNum
     * @return
     */
    private String getPercentage(Long optionNum, Long allVoteNum){
        double optionNum1 = optionNum*1.0;
        double allVoteNum1 = allVoteNum*1.0;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        String format = numberFormat.format(optionNum1 / allVoteNum1);
        return format.replace("%","") ;
    }
//    private List<VoteResult> getResByUserIdAndOptionId(List<PoVoteOption> optionId){
//        List<VoteResult> results = mapper.getResByUserIdAndOptionId(optionId);
//        Iterator<VoteResult> iterator = results.iterator();
//        while (iterator.hasNext()){
//            if(!RSATool.decrypt(iterator.next().getUserId()).equals(getUserId())){
//                iterator.remove();
//            }
//        }
//        return results;
//    }
}
