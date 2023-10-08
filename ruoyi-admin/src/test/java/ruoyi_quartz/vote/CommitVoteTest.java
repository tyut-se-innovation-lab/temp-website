package ruoyi_quartz.vote;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.system.service.ISysRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Big-Bai
 **/
@SpringBootTest(classes = RuoYiApplication.class)
public class CommitVoteTest {
    @Autowired
    ISysRoleService sysRoleService;
    @Test
    public void getRoleByIdTest(){
        for (SysRole sysRole : sysRoleService.selectRolesByUserId(100L)) {
            System.out.println(sysRole.getRoleName());
        }
    }
}
