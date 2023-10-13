package tyut.selab.vote.mapper;

import java.util.List;

public interface RemoveWasteVoteMapper {
    List<Long> getWasteVote(String status);
    List<Long> getWasteVoteOptions(List<Long> voteInfos);

    void deleteVoteInfoById(List<Long> wasteVoteId);
    void deleteVoteOptionsById(List<Long> wasteVoteId);
    void deleteVoteResultById(List<Long> wasteVoteId);
}
