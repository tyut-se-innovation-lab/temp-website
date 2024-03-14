package tyut.selab.suggestion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import tyut.selab.suggestion.domain.entity.SuggestionEntity;

import java.util.List;

/**
 * @ClassName: SuggestionMapper
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:25
 * @Version: 1.0
 **/
@Mapper
public interface SuggestionMapper extends BaseMapper<SuggestionEntity> {
    @Select("select * from selab_suggestion")
    @Results({
            @Result(property = "suggestionId", column = "suggestion_id"),
            @Result(property = "suggestionContent", column = "suggestion_content"),
            @Result(property = "suggestionUser", column = "suggestion_user"),
            @Result(property = "creatTime", column = "creat_time")
            // 其他属性的映射
    })
    List<SuggestionEntity> selectAllSuggestion();

    @Select(value = "select * from selab_suggestion where suggestion_user=#{suggestionUser}")
    @Results({
            @Result(property = "suggestionId", column = "suggestion_id"),
            @Result(property = "suggestionContent", column = "suggestion_content"),
            @Result(property = "suggestionUser", column = "suggestion_user"),
            @Result(property = "creatTime", column = "creat_time")
            // 其他属性的映射
    })
    List<SuggestionEntity> selectSuggestionByUser(String  suggestionUser);

    @Insert("insert into selab_suggestion(suggestion_content,suggestion_user) values(#{suggestionContent},#{suggestionUser})")
    int insertSuggestion(SuggestionEntity suggestionEntity);

    @Select(value = "select * from selab_suggestion where suggestion_id= #{suggestionId}")
    @Results({
            @Result(property = "suggestionId", column = "suggestion_id"),
            @Result(property = "suggestionContent", column = "suggestion_content"),
            @Result(property = "suggestionUser", column = "suggestion_user"),
            @Result(property = "creatTime", column = "creat_time")
            // 其他属性的映射
    })
    SuggestionEntity selectSuggestionById(int  suggestionId);


    @Update("update selab_suggestion  set suggestion_content = #{suggestionContent} where suggestion_id = #{suggestionId} ")
    int updateSuggestionKey(@Param("suggestionId") int suggestionId, @Param("suggestionContent") String suggestionContent);



}
