package tyut.selab.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;
import tyut.selab.schedule.mapper.DisplayLeisureMapper;
import tyut.selab.schedule.service.IDisplayLeisureService;

import java.util.List;
import java.util.Map;

@Service
public class DisplayLeisureServiceImpl implements IDisplayLeisureService {

    @Autowired
    private DisplayLeisureMapper displayLeisureMapper;

    public Map<TimeFrame, List<DisplayLeisureResponse>> getLeisure(DisplayLeisureRequest displayLeisureRequest){
        List<TimeFrame> timeFrames = displayLeisureRequest.getTimeFrames();
        List<Long> deptIds = displayLeisureRequest.getDeptIds();
        List<Long> roleIds = displayLeisureRequest.getRoleIds();
        List<Long> roleIdByDeptId = displayLeisureMapper.getRoleIdByDeptId(deptIds);
        List<Long> userIds = null;
        Map<TimeFrame,List<DisplayLeisureResponse>> responseMap = null;

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
                List<Long> user_NoClass = userIds;
                List<Long> userIds_hasClass = null;
                userIds_hasClass.addAll(displayLeisureMapper.getUserIdByTimeFrame(timeFrame,userIds));
                user_NoClass.removeAll(userIds_hasClass);
                List<DisplayLeisureResponse> response = displayLeisureMapper.getResponseByUserId(user_NoClass);
                responseMap.put(timeFrame,response);
            }
        }
        return responseMap;

    }

}
