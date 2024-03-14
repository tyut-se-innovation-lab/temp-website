package tyut.selab.suggestion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.AjaxResult;
import tyut.selab.suggestion.domain.PageParam;
import tyut.selab.suggestion.domain.entity.SuggestionEntity;

/**
 * @ClassName: ISuggestionService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:33
 * @Version: 1.0
 **/
public interface ISuggestionService extends IService<SuggestionEntity> {
    AjaxResult getallSuggestion(PageParam pageParam);

    AjaxResult addSuggestion(SuggestionEntity suggestion) throws Exception;

    AjaxResult verifySuggestionKey(String suggestionKey);

    AjaxResult reviseSuggestionKey(String suggestionKey);

    AjaxResult getSuggestionById(Integer suggestionId);
}
