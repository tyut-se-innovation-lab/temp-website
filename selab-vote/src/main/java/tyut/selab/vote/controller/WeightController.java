package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Big_bai on 2022/12/28
 */
@RestController
@RequestMapping("/selab/vote/weight")
public class WeightController {

    @PreAuthorize("@ss.hasPermi('vote:management')")
    @GetMapping("/get")
    public AjaxResult setWeight(){
        return new AjaxResult(200,"权重修改中，请稍后");
    }

}
