package tyut.selab.schedule.service.impl;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.mapper.SysDeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.DisplayLeisureRequest;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;
import tyut.selab.schedule.mapper.DisplayLeisureMapper;
import tyut.selab.schedule.service.IDisplayLeisureService;

import java.util.*;

@Service
public class DisplayLeisureServiceImpl implements IDisplayLeisureService {

    @Autowired
    private DisplayLeisureMapper displayLeisureMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public List<DisplayLeisureResponse> getLeisure(DisplayLeisureRequest displayLeisureRequest) {
        List<TimeFrame> timeFrames = displayLeisureRequest.getTimeFrames();
        List<Long> deptIds = displayLeisureRequest.getDeptIds();
        List<Long> childDeptIds = new ArrayList<>();
        List<Long> roleIds = displayLeisureRequest.getRoleIds();
        List<Long> roleIdByDeptId = new ArrayList<>();
        List<Long> userIds = new LinkedList<>();
        List<DisplayLeisureResponse> response = new ArrayList<>();

        if (deptIds != null) {
            for (Long dept : deptIds) {
                List<SysDept> sysDepts = sysDeptMapper.selectChildrenDeptById(dept);
                for (SysDept sysdept : sysDepts) {
                    childDeptIds.add(sysdept.getDeptId());
                }
            }
            childDeptIds.addAll(deptIds);
            roleIdByDeptId = displayLeisureMapper.getRoleIdsByDeptId(childDeptIds);
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
        if (!childDeptIds.isEmpty()) {
            userIds.addAll(displayLeisureMapper.getUserIdByDeptId(childDeptIds));
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
        if (deptIds != null) {
            System.out.println("deptIds" + Arrays.toString(deptIds.toArray()));
        }
        if (roleIds != null) {
            System.out.println("roleIds" + Arrays.toString(roleIds.toArray()));
        }
        if (timeFrames != null) {
            System.out.println("timeFrames" + Arrays.toString(timeFrames.toArray()));
        }
        if (userIds != null) {
            System.out.println("userIds" + Arrays.toString(userIds.toArray()));
        }
        return response;

    }

}
