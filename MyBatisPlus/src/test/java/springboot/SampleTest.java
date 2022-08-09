package springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springboot.entity.User;
import springboot.mapper.UserMapper;
import org.junit.*;

import org.springframework.boot.test.context.SpringBootTest;
import springboot.service.UserService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: TODO
 * CreateTime: 2022/4/26 14:15
 * @author: dxz
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyBatisPlusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Test
    public void testSelect() {
        System.out.println("--------------------");
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(8, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(123456L);
        user.setAge(9447);
        user.setEmail("120444@qq.com");
        user.setName("zhangsa4444n");
        userMapper.updateById(user);
    }

    @Test
    public void testSelectById(){
        User user =userMapper.selectById(2);
        System.out.println(user);
    }

    // 测试批量查询
    @Test
    public void testSelectByBatchId(){
        List<User> user =userMapper.selectBatchIds(Arrays.asList(2,3));
        user.forEach(System.out::println);
    }

    // 条件查询
    @Test
    public void testSelectByBatchIds(){
        HashMap<String,Object> map=new HashMap<>();
        //自定义查询
        map.put("name","shuishui");
        map.put("age",3);

        List<User> user = userMapper.selectByMap(map);
        user.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        // 参数一：当前页
        // 参数二：页面大小
        // 使用了分页插件之后，所有的分页操作也变得简单了
        Page<User> page =new Page<>(2,5);
        userMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);
        //获取总数
        page.getTotal();
    }

    @Test
    public void testDeleteById(){
        userMapper.deleteById(3);
    }

    //批量删除
    @Test
    public void testDeleteBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1,2));
    }

    //条件删除
    @Test
    public void testDeleteMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","shuishui");
        userMapper.deleteByMap(map);
    }



    //取一个值
    @Test
    public void getOne(){
//        User one = userService.getOne(Wrapper<User>lambdaQuery().gt(User::getAge,25),false);
    }

    //批量插入
    @Test
    public void batch(){
        User user1= new User();
        user1.setName("shui");
        user1.setAge(29);

        User user2= new User();
        user2.setName("shui2");
        user2.setAge(33);

        List<User> userList =Arrays.asList(user1,user2);
        userService.saveBatch(userList);
    }

    @Test
    public void chain(){
        //查询
        userService.lambdaQuery().ge(User::getAge,25).like(User::getName,"雨").list();
    }

    @Test
    public void context() {
        // 查询name不为null的用户，并且邮箱不为null的永不，年龄大于等于20的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name");
        wrapper.isNotNull("email");
        wrapper.ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void test2() {
        // 查询name为shuishui的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "shuishui");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    @Test
    public void test3() {
        // 查询年龄在20-30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        Long count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    @Test
    public void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 相当于not like '%s%'
        wrapper.notLike("name", "s");
        // 相当于like '%s%'
        wrapper.like("email", "s");
        // 查询结果数
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 自查询
        wrapper.inSql("id", "select id from user where id<3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }



    @Test
    public void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行排序
        wrapper.orderByAsc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test7() {
        // 姓网年龄大于等于25，按年龄排序，年龄相同按id升序排列
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}
