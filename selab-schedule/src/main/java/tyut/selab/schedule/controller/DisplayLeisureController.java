package tyut.selab.schedule.controller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.service.IDisplayLeisureService;

@RestController
@RequestMapping("/selab/schedule/leisure")
public class DisplayLeisureController extends BaseController {

    @Autowired
    private IDisplayLeisureService displayLeisureService;

    @PreAuthorize("@ss.hasPermi('schedule:leisure')")
    @GetMapping("/display")
    public AjaxResult getLeisure(DisplayLeisureRequest displayLeisureRequest){
        return AjaxResult.success(displayLeisureService.getLeisure(displayLeisureRequest));
    }


}
