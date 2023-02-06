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
    @Autowired
    private FindInfoDBMapper findInfoDBMapper;
    @Autowired
    private InsertInfoDBMapper insertInfoDBMapper;
    @Autowired
    private ChangeInfoDBMapper changeInfoDBMapper;
    @Override
    public void setVoteWeight(WeightPo w) {
        if(w.equals(getNowVoteWeight())){
            return;
        }
        insertInfoDBMapper.writeWightInfoToDB(w);
        changeInfoDBMapper.writeLastUseWeightToDB(w.getId());
    }

    @Override
    public WeightPo getNowVoteWeight() {
        long a = findInfoDBMapper.getLastUseWeightId();
        return findInfoDBMapper.getWeightById(findInfoDBMapper.getLastUseWeightId());
    }

    @Override
    public int getWeightByUserId(String id) {
        return getNowVoteWeight().getWeightMap().get(getRoleThisId(id));
    }

    private int getRoleThisId(String id){
        return findInfoDBMapper.getRoleById(id);
    }
}
