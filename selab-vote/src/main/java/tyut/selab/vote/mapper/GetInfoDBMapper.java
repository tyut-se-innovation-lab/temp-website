package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.VoteUser;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;

import java.util.List;
public interface GetInfoDBMapper {
    //获取投票基本信息（根据投票ID）
    VoteInfo getInfoByVoteId(Long voteId);
    //获取投票基本信息（根据投票ID列表）
    List<VoteInfo> getInfoByVoteIdList(List<Long> voteId);
    //获取所有投票（基本信息）
    List<VoteInfo> displayAllVote();
    //获取所有未过期的投票（基本信息）
    List<VoteInfo> displayAllUsefulVote();
    //获取投票的问题
    List<PoVoteOption> getQueByVoteId(Long voteId);
    //获取投票的选项
    List<PoVoteOption> getOptionByParentId(Long parentId);
    //获取用户参加过的投票序号
    List<VoteUser> getJoinedVoteId(Long userId);
    //获取用户参加过的投票序号
    int getJoinedVoteNum(@Param("userId")String userId, @Param("voteId")Long voteId);
    //找到选择某个选项的userId
    List<VoteResult> getWeightByOption(Long optionId);
    List<VoteResult> getWeightByOptions(@Param("optionId") List<PoVoteOption> optionId);
    //获取被选中的选项
//    List<VoteResult>getResByUserIdAndOptionId(@Param("optionsId") List<PoVoteOption>optionsId);
    //参加投票的人数
    int theNumOfJoinVote(Long voteId);


}
