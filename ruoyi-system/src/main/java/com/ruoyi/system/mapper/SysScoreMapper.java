package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysScoreMapper {
    @Select("select scores from rule_score where user_id = #{userId}")
    public Integer getByUserId(Long userId);
}
