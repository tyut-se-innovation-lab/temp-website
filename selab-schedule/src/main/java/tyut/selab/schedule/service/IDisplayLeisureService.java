package tyut.selab.schedule.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;

import java.util.List;
import java.util.Map;

public interface IDisplayLeisureService {
    Map<TimeFrame,List<DisplayLeisureResponse>> getLeisure(DisplayLeisureRequest displayLeisureRequest);
}
