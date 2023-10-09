package tyut.selab.rule.service;

import com.ruoyi.common.core.domain.R;

public interface UserService {
    //查看用户的当前分数和与该用户相关的操作
    R userInfo(Long userId);
}
