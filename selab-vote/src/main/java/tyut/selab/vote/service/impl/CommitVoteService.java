package tyut.selab.vote.service.impl;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.Weight;
import tyut.selab.vote.domain.vo.JoinQuestionnaire;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.mapper.GetInfoDBMapper;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.mapper.WeightMapper;
import tyut.selab.vote.service.ICommitVoteService;
import tyut.selab.vote.tools.GetSysTime;
import tyut.selab.vote.tools.impl.RSATool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Big-Bai
 **/
@Service
public class CommitVoteService implements ICommitVoteService {

    @Autowired
    private ISysRoleService sysRoleService;
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
    public Boolean recordSelection(Questionnaire questionnaire,Long user) {
        this.voteQues = questionnaire.getVoteQues();
        this.userId = RSATool.encrypt(user.toString());
        putUserWeight(user);
        putVoteResults();
        insertInfoDBMapper.writeVoteResultToDB(voteResults);
        return true;
    }

    @Override
    public List<JoinQuestionnaire> listAllowed(Long user) {
        List<JoinQuestionnaire> allAllowed = new ArrayList<>();
        List<VoteInfo> allowed = getInfoDBMapper.displayAllUsefulVote();
        for (VoteInfo vin:allowed) {
            JoinQuestionnaire join = new JoinQuestionnaire();
            join.setId(vin.getId());
            join.setTitle(vin.getTitle());
            join.setState(vin.getStatus());
            join.setStart(vin.getCreateTime());
            join.setEnd(vin.getDeadline());
            join.setContent(vin.getContent());
            join.setIsJoin(showDetailedVoteListService.isJoin(vin.getId()));
            allAllowed.add(join);
        }
        return allAllowed;
    }

    private void putVoteResults(){
        voteResults = new ArrayList<>();
        for (VoteQue vq:voteQues) {
            for(VoteOption vo:vq.getOptions()){
                VoteResult vr = new VoteResult();
                vr.setVoteOptionId(vq.getId());
                vr.setUserId(userId);
                vr.setContent(vr.getContent());
                vr.setIsEnable(1);
                vr.setCreateTime(GetSysTime.getNow());
                vr.setWeight(userWeight);
            }
        }
    }

    private void putUserWeight(Long user){
        List<Weight> weights = weightMapper.getWeightList(weightMapper.getLastUseWeightId());
        List<SysRole> roles = sysRoleService.selectRolesByUserId(user);
        Map<Long,Integer> roleWeight = new HashMap<>();
        for(Weight w:weights){
            roleWeight.put(w.getRoleId(),w.getWeight());
        }

        int weightThisRole = 0;
        for(SysRole ro:roles){
            int num = roleWeight.get(ro.getRoleId());
            if(num>weightThisRole){
                weightThisRole = num;
            }
        }
        userWeight = weightThisRole;
    }
}
