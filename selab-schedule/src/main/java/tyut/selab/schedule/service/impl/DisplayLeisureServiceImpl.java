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

    List<Long> childDeptIds = new ArrayList<>();
    List<Long> roleIdByDeptId = new ArrayList<>();
    List<Long> userIds = new LinkedList<>();
    List<DisplayLeisureResponse> response = new ArrayList<>();

    public List<DisplayLeisureResponse> getLeisure(DisplayLeisureRequest displayLeisureRequest) {

        List<TimeFrame> timeFrames = displayLeisureRequest.getTimeFrames();
        List<Long> deptIds = displayLeisureRequest.getDeptIds();
        List<Long> roleIds = displayLeisureRequest.getRoleIds();

        getRoleIdsByDeptIds(deptIds);
        getUserIdsByRoleIdsAndDeptIds(roleIds,deptIds);
        getUserIdsByTimeFrame(timeFrames);

        //根据用户Id查出响应数据
        return displayLeisureMapper.getResponseByUserId(userIds);

    }

    /**
     * 用部门Id获取子部门，用子部门Id去查找部门下的角色Id
     *
     * @param deptIds 部门Id
     */
    private void getRoleIdsByDeptIds(List<Long> deptIds) {
        if (Objects.nonNull(deptIds)) {
            for (Long dept : deptIds) {
                List<SysDept> sysDepts = sysDeptMapper.selectChildrenDeptById(dept);
                for (SysDept sysdept : sysDepts) {
                    childDeptIds.add(sysdept.getDeptId());
                }
                sysDepts.stream()
                        .map(SysDept::getDeptId)
                        .forEach(childDeptIds::add);
            }
            childDeptIds.addAll(deptIds);
            roleIdByDeptId = displayLeisureMapper.getRoleIdsByDeptId(childDeptIds);
        }
    }

    /**
     * 根据筛选出来的角色Id和部门Id去查找对应的用户Id
     *
     * @param roleIds 角色Id
     * @param deptIds 部门Id
     */
    private void getUserIdsByRoleIdsAndDeptIds(List<Long> roleIds, List<Long> deptIds) {
        if (Objects.nonNull(roleIds)) {
            roleIds.removeIf(roleId -> roleIdByDeptId.contains(roleId));
        }

        if (Objects.nonNull(roleIds)) {
            userIds.addAll(displayLeisureMapper.getUserIdByRoleId(roleIds));
        }
        if (!childDeptIds.isEmpty()) {
            userIds.addAll(displayLeisureMapper.getUserIdByDeptId(childDeptIds));
        }
    }

    /**
     * 根据时间条件筛选有课的用户Id
     * @param timeFrames 时间条件
     */
    private void getUserIdsByTimeFrame(List<TimeFrame> timeFrames) {
        if (Objects.nonNull(timeFrames)) {
            for (TimeFrame timeFrame : timeFrames) {
                List<Long> userIds_hasClass = null;
                userIds_hasClass.addAll(displayLeisureMapper.getUserIdByTimeFrame(timeFrame, userIds));
                userIds.removeAll(userIds_hasClass);
            }
        }
    }

}
