package tyut.selab.rule.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.rule.service.UserService;

@RestController
@RequestMapping("/rule")
public class UserController {
    //查看用户的当前分数和与该用户相关的操作
    @Autowired
    private UserService userService;
    @PreAuthorize("@ss.hasAnyPermi('rule:score:check')")
    @GetMapping("/userInfo")
    public AjaxResult userInfo(@RequestParam("userId") Long userId) {
        return userService.userInfo(userId);
    }
}
