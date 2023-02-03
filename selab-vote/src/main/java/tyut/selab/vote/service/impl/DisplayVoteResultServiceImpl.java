package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.tools.AnonymousControl;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class DisplayVoteResultServiceImpl implements IDisplayVoteResultService {
    @Autowired
    FindInfoDBMapper findInfoDBMapper;
    @Autowired
    FindInfoDBMapper displayAllVoteMapper;
    @Autowired
    WeightControlService weight;

    /**（暂时弃用）（查看某人投票的详细信息）
     * 返回某次投票详细信息
     * 返回我发起过的投票详细信息
     * 我参与的投票详细信息
     * @param voteId 投票ID
     * @param userId 申请者id
     * @return
     */
    @Override
    public Questionnaire displayVoteResult(Long voteId,Long userId) {
        VoPoConverterTool tool = new VoPoConverterTool();
        // 根据投票id获取到 info（问卷）
        VoteInfo voteByVoteId = findInfoDBMapper.getVoteByVoteId(voteId);
        //转换
        Questionnaire info = tool.info(voteByVoteId);
        //根据infoId找到option
        List<PoVoteOption> voteOptions = findInfoDBMapper.getVoteOptions(voteByVoteId.getId());
        List<VoteQue> que = tool.que(voteOptions);
        for (VoteQue voteQue : que) {
            List<PoVoteOption> voteOptionByParentId = findInfoDBMapper.getVoteOptionByParentId(voteQue.getId());
            List<VoteResult> resByUserIdAndOptionId = findInfoDBMapper.getResByUserIdAndOptionId(userId, voteOptionByParentId);
            List<VoteOption> options = tool.options(voteOptionByParentId,resByUserIdAndOptionId);
            voteOptionByParentId.forEach(System.out::println);
            voteQue.setOptions(options);
        }
        info.setVoteQues(que);
        return info;
    }

    /**
     * 参与投票的总人数
     * @param voteId 投票ID
     * @param userId 申请者ID
     * @return
     */
    @Override
    public int displayNumOfResult(Long voteId, Long userId) {
        int peoples = findInfoDBMapper.theNumOfJoinVote(voteId);
        return peoples;
    }

    /**
     * 查看正在进行投票的详细信息(未测试）
     * @param voteId 投票ID
     * @param userId 使用者ID
     * @return
     */
    @Override
    public Questionnaire displayVoteGoing(Long voteId, Long userId) {
        VoPoConverterTool tool = new VoPoConverterTool();
        VoteInfo voteByVoteId = findInfoDBMapper.getVoteByVoteId(voteId);
        Questionnaire info = tool.info(voteByVoteId);
        info.setIsWithdraw(isWithdraw(voteId,userId));
        info.setPeoples(findInfoDBMapper.theNumOfJoinVote(voteId));
        List<PoVoteOption> voteOptions = findInfoDBMapper.getVoteOptions(voteByVoteId.getId());
        List<VoteQue> que = tool.que(voteOptions);
        for (VoteQue voteQue : que) {
            List<PoVoteOption> voteOption = findInfoDBMapper.getVoteOptionByParentId(voteQue.getId());
            for (PoVoteOption poVoteOption : voteOption) {
                poVoteOption.setPercentage(getPercentage(getVotesByVoteId(poVoteOption.getId()),getTotalVotes(voteOption)));
            }
            List<VoteResult> resByUserIdAndOptionId = findInfoDBMapper.getResByUserIdAndOptionId(userId, voteOption);
            List<VoteOption> options = tool.options(voteOption,resByUserIdAndOptionId);
            voteOption.forEach(System.out::println);
            voteQue.setOptions(options);
        }
        info.setVoteQues(que);
        return info;
    }

    /**
     * 查看历史投票的详细信息(未测试)
     * @param voteId 投票ID
     * @param userId 使用者ID
     * @return
     */
    @Override
    public Questionnaire displayVoteHistory(Long voteId, Long userId) {
        VoPoConverterTool tool = new VoPoConverterTool();
        VoteInfo voteByVoteId = findInfoDBMapper.getVoteByVoteId(voteId);
        Questionnaire info = tool.info(voteByVoteId);
        info.setIsWithdraw(isWithdraw(voteId,userId));
        info.setPeoples(findInfoDBMapper.theNumOfJoinVote(voteId));
        List<PoVoteOption> voteOptions = findInfoDBMapper.getVoteOptions(voteByVoteId.getId());
        List<VoteQue> que = tool.que(voteOptions);
        for (VoteQue voteQue : que) {
            List<PoVoteOption> voteOption = findInfoDBMapper.getVoteOptionByParentId(voteQue.getId());
            for (PoVoteOption poVoteOption : voteOption) {
                poVoteOption.setPercentage(getPercentage(getVotesByVoteId(poVoteOption.getId()),getTotalVotes(voteOption)));
            }
            List<VoteOption> options = tool.option(voteOption);
            voteOption.forEach(System.out::println);
            voteQue.setOptions(options);
        }
        info.setVoteQues(que);
        return info;
    }

    /**
     * 获取百分比
     * @param optionNum
     * @param allVoteNum
     * @return
     */
    private String getPercentage(Long optionNum,Long allVoteNum){
        double optionNum1 = optionNum*1.0;
        double allVoteNum1 = allVoteNum*1.0;
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        String format = numberFormat.format(optionNum1 / allVoteNum1);
        return format;
    }

    /**
     * 获取某个问题全部的票数
     * @param optionId
     * @return
     */
    private Long getTotalVotes(List<PoVoteOption>optionId){
        Long totalVote = 0L;
        List<VoteResult> user = findInfoDBMapper.displayVoteUser(optionId);
        for (VoteResult userId : user) {
            totalVote+=weight.getWeightByUserId(userId.getUserId());
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
        List<VoteResult> users = findInfoDBMapper.displayVoteUsers(optionId);
        for (VoteResult user : users) {
            num+= weight.getWeightByUserId(user.getUserId());
        }
        return num;
    }

    /**
     * 是否支持撤回
     * @param voteid
     * @param userId
     * @return
     */
    private int isWithdraw(long voteid,long userId){
        int role = findInfoDBMapper.getRoleById(userId);
        VoteInfo vote = findInfoDBMapper.getVoteByVoteId(voteid);
        if(role==1||role==100){
            return 1;
        }else {
            if(vote.getUserId().equals(userId)){
                return 1;
            }else return 0;
        }
    }

    /**
     * 根据voteId判断用户是否参与投票
     * @param userId
     * @param voteId
     * @return
     */
    @Override
    public Boolean isJoin(Long userId, Long voteId) {
        Long Id = null;
        try {
            //Id = AnonymousControl.encrypt(voteId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return findInfoDBMapper.isJoin(userId,Id) != 0 ? true:false;
    }
}
