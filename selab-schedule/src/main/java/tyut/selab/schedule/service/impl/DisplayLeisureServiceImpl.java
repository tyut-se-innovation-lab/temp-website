package tyut.selab.schedule.service.impl;

import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;
import tyut.selab.schedule.mapper.DisplayLeisureMapper;
import tyut.selab.schedule.service.IDisplayLeisureService;

import java.util.List;

@Service
public class DisplayLeisureServiceImpl implements IDisplayLeisureService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private DisplayLeisureMapper displayLeisureMapper;

    public List<DisplayLeisureResponse> getLeisure(DisplayLeisureRequest displayLeisureRequest){
        List<TimeFrame> timeFrames = displayLeisureRequest.getTimeFrames();
        List<Long> deptIds = displayLeisureRequest.getDeptIds();
        List<Long> roleIds = displayLeisureRequest.getRoleIds();
        List<Long> roleIdByDeptId = displayLeisureMapper.getRoleIdByDeptId(deptIds);
        List<Long> userIds = null;
        List<Long> userIds_filtration = null;
        if (roleIds!=null) {
            for (Long roleId : roleIds) {
                if (roleIdByDeptId.contains(roleId)){
                    roleIds.remove(roleId);
                }
            }
        }
        if (roleIds!=null){
            userIds = displayLeisureMapper.getUserIdByRoleId(roleIds);
        }
        if (deptIds!=null) {
            userIds.addAll(displayLeisureMapper.getUserIdByDeptId(deptIds));
        }
        if (timeFrames!=null) {
            for (TimeFrame timeFrame : timeFrames) {
                userIds_filtration.addAll(displayLeisureMapper.getUserIdByTimeFrame(timeFrame,userIds));
            }
        }

        return displayLeisureMapper.getResponseByUserId(userIds_filtration);
    }

}
