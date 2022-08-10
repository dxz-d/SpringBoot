package springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.annotations.LoginRequired;
import springboot.annotations.PermissionRequired;
import springboot.entity.User;
import springboot.enu.Logical;
import springboot.enu.UserType;
import springboot.enu.WebConstant;
import springboot.mapper.UserMapper;
import springboot.result.Result;

import javax.servlet.http.HttpSession;

/**
 * Description: TODO
 * CreateTime: 2022/8/9 17:55
 *
 * @author: dxz
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpSession session;

    @PostMapping("/register")
    public Result<User> register(@RequestBody User userInfo) {
        int rows = userMapper.insert(userInfo);
        if (rows > 0) {
            return Result.success(userInfo);
        }

        return Result.error("插入失败");
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody User loginInfo) {
        // 用的是MyBatis-Plus
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getName, loginInfo.getName());
        lambdaQuery.eq(User::getPassword, loginInfo.getPassword());

        User user = userMapper.selectOne(lambdaQuery);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }

        session.setAttribute(WebConstant.CURRENT_USER_IN_SESSION, user);
        return Result.success(user);
    }

    @LoginRequired
    @GetMapping("/needLogin")
    public Result<String> needLogin() {
        return Result.success("if you see this, you are logged in.");
    }

    @GetMapping("/needNotLogin")
    public Result<String> needNotLogin() {
        return Result.success("if you see this, you are logged in.");
    }

}
