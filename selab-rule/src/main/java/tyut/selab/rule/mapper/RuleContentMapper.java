package tyut.selab.rule.mapper;

import tyut.selab.rule.domain.PO.RuleContent;

/**
* @author Otion
* @description 针对表【rule_content】的数据库操作Mapper
* @createDate 2023-11-21 13:26:15
* @Entity tyut.selab.rule.domain.PO.RuleContent
*/
public interface RuleContentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RuleContent record);

    int insertSelective(RuleContent record);

    RuleContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RuleContent record);

    int updateByPrimaryKey(RuleContent record);

}
