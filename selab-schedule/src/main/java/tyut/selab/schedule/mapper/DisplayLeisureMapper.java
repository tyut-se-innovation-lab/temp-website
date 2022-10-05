package tyut.selab.schedule.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.vo.DisplayLeisureResponse;

import java.util.List;

public interface DisplayLeisureMapper {
     List<Long> getUserByTimeFrame(@Param("timeFrame") TimeFrame timeFrame,@Param("userIds") List<Long> userIds);

     List<Long> getUserIdByDeptId(@Param("deptIds") List<Long> deptIds);

     List<Long> getUserIdByRoleId(@Param("roleIds") List<Long> roleIds);

     List<Long> getRoleIdByDeptId(@Param("deptIds") List<Long> deptId);

     List<DisplayLeisureResponse> getResponseByUserId(@Param("userIds") List<Long> userId);
}
