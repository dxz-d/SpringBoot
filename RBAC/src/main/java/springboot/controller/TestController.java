package springboot.controller;

import org.junit.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.annotations.RequiresPermission;
import springboot.entity.User;
import springboot.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: TODO
 * CreateTime: 2022/4/26 15:20
 *
 * @author: dxz
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserMapper userMapper;

    @RequiresPermission()
    @GetMapping("/select")
    public void testSelect() {
        System.out.println("--------------------");
        List<User> userList = userMapper.selectList(null);
        // Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
