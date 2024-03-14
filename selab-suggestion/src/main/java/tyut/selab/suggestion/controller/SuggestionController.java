package tyut.selab.suggestion.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tyut.selab.suggestion.domain.PageParam;
import tyut.selab.suggestion.domain.entity.SuggestionEntity;
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

//    @PreAuthorize("@ss.hasPermi('suggestion:list')")
    @PostMapping("/list")
    public AjaxResult getAllSuggestion(@RequestBody @Validated PageParam pageParam){
        return iSuggestionService.getallSuggestion(pageParam);
    }

    @PreAuthorize("@ss.hasPermi('suggestion:add')")
    @PostMapping("/add")
    public AjaxResult addSuggestion(@RequestBody @Validated SuggestionEntity suggestionEntity) throws Exception {
        return iSuggestionService.addSuggestion(suggestionEntity);
    }

//    @PreAuthorize("@ss.hasPermi('suggestion:verify')")
    @PostMapping("/verify")
    @ResponseBody
    public AjaxResult verifySuggestionPassword(String password){
        return iSuggestionService.verifySuggestionKey(password);
    }
}
