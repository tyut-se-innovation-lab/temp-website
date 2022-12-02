package tyut.selab.vote.domain.po;

import lombok.Data;
import tyut.selab.vote.enums.VoteOptionType;

import java.util.Objects;

@Data
public class PoVoteOption {
    private Long id;
    private Long parentId;
    private Long voteId;
    private String optionType;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoVoteOption that = (PoVoteOption) o;
        return parentId.equals(that.parentId) && voteId.equals(that.voteId) && optionType.equals(that.optionType) && content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, voteId, optionType, content);
    }
}
