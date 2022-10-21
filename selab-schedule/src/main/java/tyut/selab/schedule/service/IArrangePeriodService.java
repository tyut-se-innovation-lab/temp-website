package tyut.selab.schedule.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import tyut.selab.schedule.domain.vo.ArrangePeriodRequest;

import java.util.List;

/**
 * @author 冯洋
 * @version 1.0
 */
public interface IArrangePeriodService {
    List<SysUser>[][] arrangePeriod(ArrangePeriodRequest arrangePeriodRequest);
    public List<SysUser>[][] arrangePeriodSecond(ArrangePeriodRequest arrangePeriodRequest);
}
