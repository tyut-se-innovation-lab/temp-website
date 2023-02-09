package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.mapper.RemoveWasteVoteMapper;
import tyut.selab.vote.service.IRemoveWasteVoteRegularly;

import java.util.List;

@Service
public class RemoveWasteVoteRegularly implements IRemoveWasteVoteRegularly {
    @Autowired
    private RemoveWasteVoteMapper removeWasteVote;
    @Override
    public void removeWasteVote() {
        List<Long> wasteVotes = removeWasteVote.getWasteVote("2");
        if(!wasteVotes.isEmpty()) {
            List<Long> wasteVoteOptions = removeWasteVote.getWasteVoteOptions(wasteVotes);
            removeWasteVote.deleteVoteInfoById(wasteVotes);
            removeWasteVote.deleteVoteOptionsById(wasteVotes);
            if(!wasteVoteOptions.isEmpty()){
                removeWasteVote.deleteVoteResultById(wasteVoteOptions);
            }
        }
    }
}
