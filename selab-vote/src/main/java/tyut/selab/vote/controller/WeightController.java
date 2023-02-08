package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.po.WeightPo;
import tyut.selab.vote.domain.vo.Weight;
import tyut.selab.vote.service.IWeightControlService;

/**
 * @author Big_bai on 2022/12/28
 */
@RestController
@RequestMapping("/selab/vote/weight")
public class WeightController {

    @Autowired
    IWeightControlService weightControlService;

    @PreAuthorize("@ss.hasPermi('vote:management')")
    @GetMapping("/modify")
    public AjaxResult setWeight(@RequestBody Weight weight){
        weightControlService.setVoteWeight(new WeightPo(weight));
        return new AjaxResult(200,"权重已上传，请稍后");
    }

    @PreAuthorize("@ss.hasPermi('vote:management')")
    @GetMapping("/get")
    public AjaxResult getNowWeight(){
        return  AjaxResult.success(new Weight(weightControlService.getNowVoteWeight()));
    }

}
