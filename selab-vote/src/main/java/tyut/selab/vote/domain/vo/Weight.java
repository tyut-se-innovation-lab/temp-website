package tyut.selab.vote.domain.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Big_bai on 2022/11/22
 */
@Data
public class Weight {
    //数据库中的id
    private Long id;
    //投票管理员100
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
        Weight weight = (Weight) o;
        return voteManager == weight.voteManager && DEVLeader == weight.DEVLeader && CSLeader == weight.CSLeader && DEVManager == weight.DEVManager && CSManager == weight.CSManager && DEVMember == weight.DEVMember && CSMember == weight.CSMember;
    }



    public Weight(Long id,int voteManager, int DEVLeader, int CSLeader, int DEVManager, int CSManager, int DEVMember, int CSMember) {
        this.id = id;
        this.voteManager = voteManager;
        this.DEVLeader = DEVLeader;
        this.CSLeader = CSLeader;
        this.DEVManager = DEVManager;
        this.CSManager = CSManager;
        this.DEVMember = DEVMember;
        this.CSMember = CSMember;
    }

    public Weight() {
    }

    public Map<Integer,Integer> getWeightMap(){
        Map<Integer,Integer> weightMap = new HashMap<>();
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
