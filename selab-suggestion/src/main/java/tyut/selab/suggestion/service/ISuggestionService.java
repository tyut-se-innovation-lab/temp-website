package tyut.selab.suggestion.service;

import com.github.pagehelper.PageInfo;
import tyut.selab.suggestion.domain.PageParam;
import tyut.selab.suggestion.domain.entity.SuggestionEntity;

/**
 * @ClassName: ISuggestionService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:33
 * @Version: 1.0
 **/
public interface ISuggestionService {
    PageInfo<SuggestionEntity> getallSuggestion(PageParam pageParam);

    boolean addSuggestion(SuggestionEntity suggestion) throws Exception;

    boolean verifySuggestionKey(String suggestionKey);

    boolean reviseSuggestionKey(String suggestionKey);

    SuggestionEntity getSuggestionById(Integer suggestionId);

    boolean deleteSuggestionById(Integer suggestionId);
}
