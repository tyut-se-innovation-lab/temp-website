package tyut.selab.vote.service.impl;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.tools.GetSysTime;
import tyut.selab.vote.tools.impl.RSATool;

import java.util.*;

/**
 * @author Big-Bai
 **/
@Service
public class CommitVoteService implements ICommitVoteService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private WeightMapper weightMapper;
    @Autowired
    private InsertInfoDBMapper insertInfoDBMapper;
    @Autowired
    private GetInfoDBMapper getInfoDBMapper;
    @Autowired
    private ShowDetailedVoteListService showDetailedVoteListService;
    private List<VoteResult> voteResults;
    private List<VoteQue> voteQues;
    private String userId;
    private int userWeight;

    @Override
    public Boolean recordSelection(Questionnaire questionnaire, Long user) {
        if(showDetailedVoteListService.isJoin(questionnaire.getId())){
            throw new RuntimeException("请不要重复提交");
        }
        this.voteQues = questionnaire.getVoteQues();
        this.userId = RSATool.encrypt(user.toString());
        insertInfoDBMapper.writeParticipateToDB(questionnaire.getId(), user);
        putUserWeight(user);
        putVoteResults();
        insertInfoDBMapper.writeVoteResultToDB(voteResults);
        return true;
    }

    @Override
    public List<JoinQuestionnaire> listAllowed(Long user) {
        List<JoinQuestionnaire> allAllowed = new ArrayList<>();
        List<VoteInfo> allowed = getInfoDBMapper.displayAllUsefulVote();

        for (VoteInfo vin : allowed) {
            if (vin.getDeadline().after(new Date()) && !showDetailedVoteListService.isJoin(vin.getId())) {
                JoinQuestionnaire join = new JoinQuestionnaire();
                join.setId(vin.getId());
                join.setTitle(vin.getTitle());
                join.setState(vin.getStatus());
                join.setCreatTime(vin.getCreateTime());
                join.setDeadline(vin.getDeadline());
                join.setContent(vin.getContent());
                join.setIsJoin(showDetailedVoteListService.isJoin(vin.getId()));
                allAllowed.add(join);
            }
        }
        return allAllowed;
    }

    private void putVoteResults() {
        voteResults = new ArrayList<>();
        for (VoteQue vq : voteQues) {
            if ("T".equals(vq.getType())) {
                VoteResult vr = new VoteResult();
                vr.setVoteOptionId(vq.getId());
                vr.setUserId(userId);
                vr.setContent(vq.getContent());
                vr.setIsEnable(1);
                vr.setCreateTime(GetSysTime.getNow());
                vr.setWeight(userWeight);
                voteResults.add(vr);
            } else {
                for (VoteOption vo : vq.getOptions()) {
                    if (vo.getIsSelect() == null || !vo.getIsSelect()) continue;
                    VoteResult vr = new VoteResult();
                    vr.setVoteOptionId(vo.getId());
                    vr.setUserId(userId);
                    vr.setContent(vo.getContent());
                    vr.setIsEnable(1);
                    vr.setCreateTime(GetSysTime.getNow());
                    vr.setWeight(userWeight);
                    voteResults.add(vr);
                }
            }

        }
    }

    private void putUserWeight(Long user) {
        List<Weight> weights = weightMapper.getWeightList(weightMapper.getLastUseWeightId());
        List<SysRole> roles = roleMapper.selectRolePermissionByUserId(user);
        Map<Long, Integer> roleWeight = new HashMap<>();
        for (Weight w : weights) {
            roleWeight.put(w.getRoleId(), w.getWeight());
        }

        int weightThisRole = 0;
        for (SysRole ro : roles) {
            int num = roleWeight.get(ro.getRoleId());
            if (num > weightThisRole) {
                weightThisRole = num;
            }
        }
        userWeight = weightThisRole;
    }
}
