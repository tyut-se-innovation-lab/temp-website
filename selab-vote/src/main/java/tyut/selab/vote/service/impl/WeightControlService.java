package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.WeightPo;
import tyut.selab.vote.domain.vo.Weight;
import tyut.selab.vote.mapper.ChangeInfoDBMapper;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.service.IWeightControlService;

import javax.annotation.PostConstruct;

/**
 * @author Big_bai on 2022/11/25
 */
@Service
public class WeightControlService implements IWeightControlService {

    @Override
    public void setVoteWeight(WeightPo w) {

    }

    @Override
    public WeightPo getNowVoteWeight() {
        return null;
    }

    @Override
    public int getWeightByUserId(String id) {
        return 0;
    }
}
