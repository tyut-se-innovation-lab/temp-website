package tyut.selab.vote.domain.vo;

/**
 * @author Big_bai on 2022/11/22
 */
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

    public Long getId() {
        return id;
    }

    public void setWeightId(Long id) {
        this.id = id;
    }

    public int getVoteManager() {
        return voteManager;
    }

    public void setVoteManager(int voteManager) {
        this.voteManager = voteManager;
    }

    public int getDEVManager() {
        return DEVManager;
    }

    public void setDEVManager(int DEVManager) {
        this.DEVManager = DEVManager;
    }

    public int getCSManager() {
        return CSManager;
    }

    public void setCSManager(int CSManager) {
        this.CSManager = CSManager;
    }

    public int getDEVLeader() {
        return DEVLeader;
    }

    public void setDEVLeader(int DEVLeader) {
        this.DEVLeader = DEVLeader;
    }

    public int getCSLeader() {
        return CSLeader;
    }

    public void setCSLeader(int CSLeader) {
        this.CSLeader = CSLeader;
    }

    public int getDEVMember() {
        return DEVMember;
    }

    public void setDEVMember(int DEVMember) {
        this.DEVMember = DEVMember;
    }

    public int getCSMember() {
        return CSMember;
    }

    public void setCSMember(int CSMember) {
        this.CSMember = CSMember;
    }
}
