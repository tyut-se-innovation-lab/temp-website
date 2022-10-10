package tyut.selab.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;
import tyut.selab.schedule.mapper.DisplayLeisureMapper;
import tyut.selab.schedule.service.IDisplayLeisureService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DisplayLeisureServiceImpl implements IDisplayLeisureService {

    @Autowired
    private DisplayLeisureMapper displayLeisureMapper;

    public List<DisplayLeisureResponse> getLeisure(DisplayLeisureRequest displayLeisureRequest) {
        List<TimeFrame> timeFrames = displayLeisureRequest.getTimeFrames();
        List<Long> deptIds = displayLeisureRequest.getDeptIds();
        List<Long> roleIds = displayLeisureRequest.getRoleIds();
        List<Long> roleIdByDeptId = new ArrayList<>();
        List<Long> userIds = new LinkedList<>();
        List<DisplayLeisureResponse> response = new ArrayList<>();

        if (deptIds != null) {
            roleIdByDeptId = displayLeisureMapper.getRoleIdsByDeptId(deptIds);
        }

        if (roleIds != null) {
            for (Long roleId : roleIds) {
                if (roleIdByDeptId.contains(roleId)) {
                    roleIds.remove(roleId);
                }
            }
        }
        if (roleIds != null) {
            userIds.addAll(displayLeisureMapper.getUserIdByRoleId(roleIds));
        }
        if (deptIds != null) {
            userIds.addAll(displayLeisureMapper.getUserIdByDeptId(deptIds));
        }
        if (timeFrames != null) {
            for (TimeFrame timeFrame : timeFrames) {
                List<Long> userIds_hasClass = null;
                userIds_hasClass.addAll(displayLeisureMapper.getUserIdByTimeFrame(timeFrame, userIds));
                userIds.removeAll(userIds_hasClass);
            }
        }
        if (!userIds.isEmpty()) {
            response = displayLeisureMapper.getResponseByUserId(userIds);
        }
        return response;

    }

}
