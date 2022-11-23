package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.vo.Weight;

/**
 * 查找信息
 * @author Big_bai on 2022/11/22
 */
public interface FindInfoDBMapper {
    Weight getWeightById(Long id);
}
