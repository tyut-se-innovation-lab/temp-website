package tyut.selab.schedule.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.schedule.service.IMyScheduleService;

/**
 * 查询我的课程信息
 *
 * @Author: Gulu
 * @Date: 2022/10/4 14:38
 */
@RestController
@RequestMapping("---------")
public class MyScheduleController extends BaseController
{
    @Autowired
    private IMyScheduleService myScheduleService;


}
