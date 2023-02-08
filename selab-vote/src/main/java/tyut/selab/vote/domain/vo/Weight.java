package tyut.selab.vote.domain.vo;

import lombok.Data;
import tyut.selab.vote.domain.po.WeightPo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Big_bai on 2022/11/22
 */
@Data
public class Weight {
    //投票管理员100
    private int Votemanager;
    //开发组组长
    private int DEVLeader;
    //网安组组长
    private int CSLeader;
    //开发组管理员
    private int DEVManager;
    //网安组管理员
    private int CSManager;
    //开发组成员
    private int DEVMember;
    //网安组成员
    private int CSMember;




    public Weight(int VoteManager, int DEVLeader, int CSLeader, int DEVManager, int CSManager, int DEVMember, int CSMember) {
        this.Votemanager = VoteManager;
        this.DEVLeader = DEVLeader;
        this.CSLeader = CSLeader;
        this.DEVManager = DEVManager;
        this.CSManager = CSManager;
        this.DEVMember = DEVMember;
        this.CSMember = CSMember;
    }

    public Weight() {
    }

    public Weight(WeightPo weightPo){
        this.Votemanager = weightPo.getVoteManager();
        this.DEVLeader = weightPo.getDEVLeader();
        this.CSLeader = weightPo.getCSLeader();
        this.DEVManager = weightPo.getDEVManager();
        this.CSManager = weightPo.getCSManager();
        this.DEVMember = weightPo.getDEVMember();
        this.CSMember = weightPo.getCSMember();
    }

    @Override
    public String toString() {
        return "{" +
                "VoteManager=" + Votemanager +
                ", DEVLeader=" + DEVLeader +
                ", CSLeader=" + CSLeader +
                ", DEVManager=" + DEVManager +
                ", CSManager=" + CSManager +
                ", DEVMember=" + DEVMember +
                ", CSMember=" + CSMember +
                '}';
    }
}
