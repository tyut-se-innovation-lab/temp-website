package tyut.selab.suggestion.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.suggestion.domain.PageParam;
import tyut.selab.suggestion.domain.entity.SuggestionEntity;
import tyut.selab.suggestion.domain.vo.SuggestionKey;
import tyut.selab.suggestion.service.ISuggestionService;


/**
 * @ClassName: SuggestionController
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-03-13 12:45
 * @Version: 1.0
 **/
//@Tag(name = "意见箱")
@Api("意见箱")
@RestController
@RequestMapping("/suggestion")
public class SuggestionController {
    @Autowired
    private ISuggestionService iSuggestionService;

    @PreAuthorize("@ss.hasPermi('suggestion:list')")
    @PostMapping("/list")
    public AjaxResult getAllSuggestion(@RequestBody @Validated PageParam pageParam){
        return AjaxResult.success(iSuggestionService.getallSuggestion(pageParam));
    }

    @PreAuthorize("@ss.hasPermi('suggestion:list')")
    @PostMapping("/list/user")
    public AjaxResult getSuggestionByUser(@RequestBody SuggestionKey suggestionId){
        return AjaxResult.success(iSuggestionService.getSuggestionById(Integer.valueOf(suggestionId.getSuggestionKey())));
    }
    @PreAuthorize("@ss.hasPermi('suggestion:add')")
    @PostMapping("/add")
    public AjaxResult addSuggestion(@RequestBody @Validated SuggestionEntity suggestionEntity) throws Exception {
        if (iSuggestionService.addSuggestion(suggestionEntity)){
            return AjaxResult.success("添加成功！");
        }
        return AjaxResult.error();
    }

    @PreAuthorize("@ss.hasPermi('suggestion:verify')")
    @PostMapping("/verify")
    public AjaxResult verifySuggestionPassword(@RequestBody SuggestionKey suggestionKey){
        if (iSuggestionService.verifySuggestionKey(suggestionKey.getSuggestionKey())){
            return AjaxResult.success("修改成功！");
        }
        return AjaxResult.error();
    }
    @PreAuthorize("@ss.hasPermi('suggestion:verify')")
    @PostMapping("/revise")
    public AjaxResult reviseSuggestionKey(@RequestBody SuggestionKey suggestionKey){
        if (iSuggestionService.reviseSuggestionKey(suggestionKey.getSuggestionKey())){
            return AjaxResult.success("修改成功！");
        }
        return AjaxResult.error();

    }

    @PreAuthorize("@ss.hasPermi('suggestion:delete')")
    @PostMapping("/delete")
    public AjaxResult deleteSuggestionById(@RequestBody SuggestionKey suggestionKey){
        if (iSuggestionService.deleteSuggestionById(Integer.valueOf(suggestionKey.getSuggestionKey()))){
            return AjaxResult.success("删除成功！");
        }
        return AjaxResult.error();
    }

}
