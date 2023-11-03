package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOpinionContent;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.VoteOptionDetailsVo;
import tyut.selab.vote.domain.DTO.VoteOptionDTO;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.enums.VoteType;
import tyut.selab.vote.exception.VoteCloseException;
import tyut.selab.vote.mapper.*;
import tyut.selab.vote.service.IShowVoteResultService;
import tyut.selab.vote.service.VoteInfoConverter;
import tyut.selab.vote.tools.AESUtil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: ShowVoteResultServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 18:14
 * @version: 1.0
 */
@Service
public class ShowVoteResultServiceImpl implements IShowVoteResultService {


    @Autowired
    QueryVoteMapper queryVoteMapper;
    @Autowired
    VoteInfoMapper voteInfoMapper;
    @Autowired
    VoteResultMapper voteResultMapper;
    @Autowired
    VoteUserMapper voteUserMapper;
    @Autowired
    VoteInfoConverter voteInfoConverter;


    @Override
    public VoteInfo getVoteInfoByVoteId(Long voteId){
//        Long userId = SecurityUtils.getUserId();
        Long userId = 1L;
        // 先查询投票状态判断投票是否正常
        VoteInfo voteStatus = voteInfoMapper.getVoteStatus(voteId);
        // 判断此用户是否已经完成
        String isCompleteByUserIdAndVoteId = voteUserMapper.getIsCompleteByUserIdAndVoteId(voteId, userId);
        VoteInfo voteInfo = queryVoteMapper.getVoteInfoByVoteId(voteId);
        if(isCompleteByUserIdAndVoteId.equals("1") && voteInfo.getUserId() == userId){

            VoteInfo voteResultDetails = getVoteResultDetailsByVoteId(voteInfo.getVoteId());
            return insertIsSelect(voteResultDetails);
        }
        if (voteStatus.getStatus().getId() == 1) {

            voteInfo.setIsComplete(isCompleteByUserIdAndVoteId);
            Date date = voteInfoMapper.queryVoteDeadTime(voteId);
            if (date.before(new Date())) {
                voteInfoMapper.updateVoteStatus(voteId, VoteStatus.FINISH);
                voteInfo.setStatus(VoteStatus.FINISH);
                if(isCompleteByUserIdAndVoteId.equals("1")){
                     voteInfo = insertIsSelect(voteInfo);
                }
                // TODO: 未参与投票结束后能不能看到投票结果
                return voteInfoForRealTime(voteInfo);
            }else {
                String isRealTime = voteInfo.getIsRealTime();
                if (isCompleteByUserIdAndVoteId.equals("1")) {
                    voteInfo = insertIsSelect(voteInfo);
                    if (isRealTime.equals("1")) {
                        return voteInfoForRealTime(voteInfo);
                    }
                }
                return voteInfo;
            }

        } else if (voteStatus.getStatus().getId() == 2) {
            voteInfo.setIsComplete(isCompleteByUserIdAndVoteId);
            if(isCompleteByUserIdAndVoteId.equals("1")){
                voteInfo = insertIsSelect(voteInfo);
            }
            return voteInfoForRealTime(voteInfo);
        }
       // 其他情况: 投票异常或关闭
        throw new VoteCloseException("投票异常冻结或已关闭");
    }

    @Override
    public VoteInfo getVoteResultDetailsByVoteId(Long voteId) {
        VoteInfo voteInfo = queryVoteMapper.getVoteInfoByVoteId(voteId);
        voteInfo = voteInfoForRealTime(voteInfo);
        return packageVoteInfo(voteInfo);
    }


    // 实时数据
    private VoteInfo voteInfoForRealTime(VoteInfo voteInfo){
        List<? extends VoteOptionDTO> voteOptionVoList = voteInfo.getVoteOptions();
        Integer allCount = voteResultMapper.getVoteReaultCount(voteInfo.getVoteId(), null);
        for (VoteOptionDTO voteOptionVo:voteOptionVoList){
            Integer voteReultCount = voteResultMapper.getVoteReaultCount(voteOptionVo.getVoteId(), voteOptionVo.getId());
            if(voteReultCount == null){
                voteReultCount = 0;
            }
            voteOptionVo.setChooseNum(voteReultCount);
            voteOptionVo.setPercentage(voteOptionPercentage(allCount,voteReultCount));
        }
        return voteInfo;
    }
    // 计算百分比
    private String voteOptionPercentage(Integer allCount,Integer voteResultCount){
        double allCount1 = allCount * 1.0;
        double voteResultCount1 = Double.valueOf(voteResultCount);
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(2);
        String format = percentInstance.format(voteResultCount1/ allCount1);
        return format.replace("%","");
    }

    // 判断是否被选中

    private  VoteInfo insertIsSelect(VoteInfo voteInfo){
//        Long userId = SecurityUtils.getUserId();
        Long userId = 1L;
        String encryptUserId = AESUtil.encrypt(userId.toString());
        List<? extends VoteOptionDTO> voteOptionVoList = voteInfo.getVoteOptions();
        List<VoteResult> voteResultByOptionIdAndVoteId = voteResultMapper.getVoteResultByOptionIdAndVoteId(voteInfo.getVoteId(),null, encryptUserId);
        for (VoteOptionDTO voteOptionVo:voteOptionVoList){
            voteOptionVo.setSelect(false);
            //判断是否被选中
            for(VoteResult voteResult:voteResultByOptionIdAndVoteId){
                if(voteResult.getVoteOptionId() == voteOptionVo.getId()){
                    voteOptionVo.setSelect(true);
                }
            }
        }
        return voteInfo;
    }
   // 包装更详细的选项信息(实名投票人姓名)
    private VoteInfo packageVoteInfo(VoteInfo voteInfo){
        VoteType voteType = voteInfo.getVoteType();
        List<VoteOptionDetailsVo> voteOptionDetailsVos = voteInfoConverter.voteOptionDtoToDetailsVo(voteInfo.getVoteOptions());
        for (VoteOptionDetailsVo voteOptionDetailsVo:voteOptionDetailsVos){

            if(voteType.equals(VoteType.SEEK_OPTIONS)){
                // 寻求意见时查询意见详情信息
                voteOptionDetailsVo.setVoteOpinionContents(insertOpinionContent(voteOptionDetailsVo.getId(),voteInfo.getVoteId()));
            }else {
                // userId全是加密后的  加一个方法 通过是否匿名去判断是否解密
                List<String> parseUserIds = voteResultMapper.getParseUserId(voteOptionDetailsVo.getId(), voteOptionDetailsVo.getVoteId());
                if(parseUserIds != null && parseUserIds.size() != 0) {
                    List<Long> userIds = parseUserIds.stream().map(AESUtil::decrypt).map(Long::parseLong).collect(Collectors.toList());
                    List<String> userNames = voteResultMapper.getNickName(userIds);
                    voteOptionDetailsVo.setUserNames(userNames);
                }
            }
        }
        voteInfo.setVoteOptions(voteOptionDetailsVos);
        return voteInfo;
    }
   // 寻求意见投票添加意见
    private List<VoteOpinionContent> insertOpinionContent(Long optionId,Long voteId){
        List<VoteOpinionContent> optionContents = voteResultMapper.getOptionContent(voteId,optionId);
        for (VoteOpinionContent voteOpinionContent:optionContents){
            if(voteOpinionContent.getIsAnonymous().equals("0")){
                Long userId = Long.valueOf(AESUtil.decrypt(voteOpinionContent.getUserId()));
                List<Long> userIds = new ArrayList<>();
                userIds.add(userId);
                voteOpinionContent.setUserName(voteResultMapper.getNickName(userIds).get(0));
            }else{
                // 目前当匿名投票时对应的投票人名字为空
            }
        }
        return optionContents;
    }

}
