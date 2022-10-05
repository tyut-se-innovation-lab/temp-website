package tyut.selab.schedule.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;

import java.util.List;

public interface DisplayLeisureMapper {
     /**
      * 传入用户ID与限制条件查询有课的用户ID
      * @param timeFrame 限制条件
      * @param userIds 筛选出来的要查询的用户ID
      * @return 有课的用户ID
      */
     List<Long> getUserIdByTimeFrame(@Param("timeFrame") TimeFrame timeFrame, @Param("userIds") List<Long> userIds);

     /**
      * 根据部门ID获取用户ID
      * @param deptIds 部门ID
      * @return 用户ID
      */
     List<Long> getUserIdByDeptId(@Param("deptIds") List<Long> deptIds);

     /**
      * 根据角色ID查找用户ID
      * @param roleIds 角色ID
      * @return 用户ID
      */
     List<Long> getUserIdByRoleId(@Param("roleIds") List<Long> roleIds);

     /**
      * 根据部门ID查找角色ID
      * @param deptId 部门ID
      * @return 角色ID
      */
     List<Long> getRoleIdByDeptId(@Param("deptIds") List<Long> deptId);

     /**
      * 根据用户ID查找结果集
      * @param userId 用户ID
      * @return Response结果集
      */
     List<DisplayLeisureResponse> getResponseByUserId(@Param("userIds") List<Long> userId);
}
