package tyut.selab.vote.domain.po;

import lombok.Data;
import tyut.selab.vote.enums.VoteOptionType;

@Data
public class PoVoteOption {
    private Long id;
    private Long parentId;
    private Long voteId;
    private String optionType;
    private String content;


}
