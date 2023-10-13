package tyut.selab.vote.domain.po;

/**
 * @className: VoteUser
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 19:22
 * @version: 1.0
 */
public class VoteUser {

    private Long voteId;
    private String userId;
    private String userName;
    // 是否完成投票 1为完成 0为未完成
    private Integer isComplete;
}
