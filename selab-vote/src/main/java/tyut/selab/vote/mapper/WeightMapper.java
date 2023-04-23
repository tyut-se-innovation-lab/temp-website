package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.Weight;

import java.util.List;

public interface WeightMapper {

    void writeWeight(List<Weight> weights);

    Long getLastUseWeightId();

    void initLastUse();

    List<Weight> getWeightList(Long weightId);

    Long getMaxWeightId();

}
