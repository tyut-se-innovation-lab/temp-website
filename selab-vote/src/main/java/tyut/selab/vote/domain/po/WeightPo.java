package tyut.selab.vote.domain.po;

import lombok.Data;
import tyut.selab.vote.domain.vo.Weight;

import java.util.HashMap;
import java.util.Map;

@Data
public class WeightPo {
    private long id;
    private int voteManager;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeightPo weight = (WeightPo) o;
        return voteManager == weight.voteManager && DEVLeader == weight.DEVLeader && CSLeader == weight.CSLeader && DEVManager == weight.DEVManager && CSManager == weight.CSManager && DEVMember == weight.DEVMember && CSMember == weight.CSMember;
    }

    public WeightPo (){}
    public WeightPo(Weight w){
        this.CSLeader=w.getCSLeader();
        this.DEVLeader=w.getDEVLeader();
        this.CSManager=w.getCSManager();
        this.DEVManager=w.getDEVManager();
        this.CSMember=w.getCSMember();
        this.DEVMember=w.getDEVMember();
        this.voteManager=w.getVoteManager();
    }

    public Map<Integer,Integer> getWeightMap(){
        Map<Integer,Integer> weightMap = new HashMap<>();
        weightMap.put(1,voteManager);
        weightMap.put(100,voteManager);
        weightMap.put(101,DEVLeader);
        weightMap.put(102,DEVManager);
        weightMap.put(103,CSLeader);
        weightMap.put(104,CSManager);
        weightMap.put(105,DEVMember);
        weightMap.put(106,CSMember);
        return weightMap;
    }
}
