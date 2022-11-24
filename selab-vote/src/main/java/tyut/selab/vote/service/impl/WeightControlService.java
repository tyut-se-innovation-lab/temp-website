package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.vo.Weight;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.service.IWeightControlService;
import tyut.selab.vote.tools.WeightControl;

/**
 * @author Big_bai on 2022/11/23
 */
@Service
public class WeightControlService implements IWeightControlService {
    @Autowired
    InsertInfoDBMapper insertInfoDBMapper ;
    @Autowired
    FindInfoDBMapper findInfoDBMapper;
    @Override
    public int setVoteWeight(Weight w) {
        Weight weight = getNowVoteWeight();
        if(w.equals(weight)){
            WeightControl.setWeight(weight.getId());
        }else{
            WeightControl.setWeight(insertInfoDBMapper.writeWightInfoToDB(w));
        }
        return 1;
    }

    @Override
    public Weight getNowVoteWeight() {
        return findInfoDBMapper.getWeightById(WeightControl.getWeight());
    }
}
