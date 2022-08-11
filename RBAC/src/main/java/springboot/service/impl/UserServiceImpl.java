package springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import springboot.entity.User;
import springboot.mapper.UserMapper;
import springboot.service.UserService;

/**
 * Description: TODO
 * CreateTime: 2022/4/26 19:10
 *
 * @author: dxz
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
