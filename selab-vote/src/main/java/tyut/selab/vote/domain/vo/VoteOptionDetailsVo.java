package tyut.selab.vote.domain.vo;

import tyut.selab.vote.domain.DTO.VoteOptionDTO;
import tyut.selab.vote.domain.po.VoteOpinionContent;

import java.util.List;

/**
 * @className: VoteOptionDetails
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 20:14
 * @version: 1.0
 */
public class VoteOptionDetailsVo extends VoteOptionDTO {


    private List<String> userNames;
    private List<VoteOpinionContent> voteOpinionContents;



    public List<String> getUserNames() {
        return userNames;
    }

    public VoteOptionDetailsVo() {
    }



    public List<VoteOpinionContent> getVoteOpinionContents() {
        return voteOpinionContents;
    }

    public void setVoteOpinionContents(List<VoteOpinionContent> voteOpinionContents) {
        this.voteOpinionContents = voteOpinionContents;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    @Override
    public String toString() {
        return super.toString()+
                "userNames=" + userNames +
                ", voteOpinionContents=" + voteOpinionContents +
                '}';
    }
}
