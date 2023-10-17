package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.VoteResultDetails;
import tyut.selab.vote.domain.vo.VoteOptionDetailsVo;
import tyut.selab.vote.domain.DTO.VoteOptionDTO;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.mapper.QueryVoteMapper;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.mapper.VoteResultMapper;
import tyut.selab.vote.mapper.VoteUserMapper;
import tyut.selab.vote.service.IShowVoteResultService;
import tyut.selab.vote.tools.AESUtil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
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
    QueryVoteMapper queryVoteMapper;
    @Autowired
    VoteInfoMapper voteInfoMapper;
    @Autowired
    VoteResultMapper voteResultMapper;
    @Autowired
    VoteUserMapper voteUserMapper;


    @Override
    public VoteInfo getVoteInfoByVoteId(Long voteId){
        Long userId = SecurityUtils.getUserId();
        // 先查询投票状态判断投票是否正常
        VoteInfo voteStatus = voteInfoMapper.getVoteStatus(voteId);
        // 判断此用户是否已经完成
        String isCompleteByUserIdAndVoteId = voteUserMapper.getIsCompleteByUserIdAndVoteId(voteId, userId);
        if (voteStatus.getStatus().getId() == 1) {
            VoteInfo voteInfo = queryVoteMapper.getVoteInfoByVoteId(voteId);
            Date date = voteInfoMapper.queryVoteDeadTime(voteId);
            if (date.after(new Date())) {
                voteInfoMapper.updateVoteStatus(voteId, VoteStatus.FINISH);
                if(isCompleteByUserIdAndVoteId.equals("1")){
                     voteInfo = insertIsSelect(voteInfo);
                }
                return voteInfoForRealTime(voteInfo);
            }else{
                String isRealTime = voteInfoMapper.getIsRealTime(voteId);
                if(isCompleteByUserIdAndVoteId.equals("1")){
                   voteInfo = insertIsSelect(voteInfo);
                   if(isRealTime.equals("1")){
                       return voteInfoForRealTime(voteInfo);
                   }
                   return voteInfo;
                }else{
                    return voteInfo;
                }
            }

        } else if (voteStatus.getStatus().getId() == 2) {
            VoteInfo voteInfo = queryVoteMapper.getVoteInfoByVoteId(voteId);
            if(isCompleteByUserIdAndVoteId.equals("1")){
                voteInfo = insertIsSelect(voteInfo);
            }
            return voteInfoForRealTime(voteInfo);
        }
       // 其他情况: 投票异常或关闭返回null
        return null;
    }

    @Override
    public VoteResultDetails getVoteResultDetailsByVoteId(Long voteId) {
        VoteInfo voteInfo = queryVoteMapper.getVoteInfoByVoteId(voteId);
        voteInfo = voteInfoForRealTime(voteInfo);
        return packageVoteInfo(voteInfo);
    }


    // 实时数据
    private VoteInfo voteInfoForRealTime(VoteInfo voteInfo){
        List<VoteOptionDTO> voteOptionVoList = voteInfo.getVoteOptionVoList();
        Integer allCount = voteResultMapper.getVoteReultCount(voteInfo.getVoteId(), null);
        for (VoteOptionDTO voteOptionVo:voteOptionVoList){
            Integer voteReultCount = voteResultMapper.getVoteReultCount(voteOptionVo.getVoteId(), voteOptionVo.getId());
            voteOptionVo.setChooseNum(voteReultCount);
            voteOptionVo.setPercentage(voteOptionPercentage(allCount,voteReultCount));
        }
        return voteInfo;
    }
    // 计算百分比
    private String voteOptionPercentage(Integer allCount,Integer voteResultCount){
        double allCount1 = allCount * 1.0;
        double voteResultCount1 = voteResultCount * 1.0;
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMinimumFractionDigits(2);
        String format = percentInstance.format(allCount1 / voteResultCount1);
        return format.replace("%","");
    }

    // 判断是否被选中

    private  VoteInfo insertIsSelect(VoteInfo voteInfo){
        Long userId = SecurityUtils.getUserId();
        List<VoteOptionDTO> voteOptionVoList = voteInfo.getVoteOptionVoList();
        List<VoteResult> voteResultByOptionIdAndVoteId = voteResultMapper.getVoteResultByOptionIdAndVoteId(voteInfo.getVoteId(),null, userId);
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
   // 包装 VoteResultDetails类
    private VoteResultDetails packageVoteInfo(VoteInfo voteInfo){

        VoteResultDetails voteResultDetails = new VoteResultDetails();
        voteResultDetails.setVoteId(voteInfo.getVoteId());
        voteResultDetails.setVoteType(voteInfo.getVoteType());
        voteResultDetails.setVoteWeights(voteInfo.getVoteWeights());
        voteResultDetails.setContent(voteInfo.getContent());
        voteResultDetails.setTitle(voteInfo.getTitle());
        voteResultDetails.setStatus(voteInfo.getStatus());
        voteResultDetails.setCreateTime(voteInfo.getCreateTime());
        voteResultDetails.setDeadTime(voteInfo.getDeadTime());
        voteResultDetails.setIsRealTime(voteInfo.getIsRealTime());
        voteResultDetails.setUserId(voteInfo.getUserId());
        voteResultDetails.setUserName(voteInfo.getUserName());
        List<VoteOptionDetailsVo> voteOptionDetails = new ArrayList<>();
        List<VoteOptionDTO> voteOptionVoList = voteInfo.getVoteOptionVoList();
        for (VoteOptionDTO voteOptionVo:voteOptionVoList){
            VoteOptionDetailsVo voteOptionDetail = new VoteOptionDetailsVo();
            voteOptionDetail.setId(voteOptionVo.getId());
            voteOptionDetail.setContent(voteOptionVo.getContent());
            voteOptionDetail.setOptionType(voteOptionVo.getOptionType());
            voteOptionDetail.setVoteNum(voteOptionVo.getChooseNum());
            voteOptionDetail.setPercentage(voteOptionVo.getPercentage());
            // userId全是加密后的  加一个方法 通过是否匿名去判断是否解密
            List<String> parseUserIds = voteResultMapper.getParseUserId(voteOptionVo.getId(),voteOptionVo.getVoteId());
            List<Long> userIds = parseUserIds.stream().map(AESUtil::decrypt).map(Long::parseLong).toList();
            List<String> userNames = voteResultMapper.getNickName(userIds);
            voteOptionDetail.setUserNames(userNames);
            voteOptionDetails.add(voteOptionDetail);
        }
        return voteResultDetails;
    }


}
