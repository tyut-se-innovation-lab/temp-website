package tyut.selab.schedule.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;

import java.util.List;

public interface IDisplayLeisureService {
    List<DisplayLeisureResponse> getLeisure(DisplayLeisureRequest displayLeisureRequest);
}
