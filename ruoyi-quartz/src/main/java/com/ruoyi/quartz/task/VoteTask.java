package com.ruoyi.quartz.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.vote.service.IRemoveWasteVoteRegularly;

@Component("voteTask")
public class VoteTask {
    @Autowired
    private IRemoveWasteVoteRegularly removeWasteVoteRegularly;
    public void removeWasteVoteRegularly(){
        removeWasteVoteRegularly.removeWasteVote();
    }
}
