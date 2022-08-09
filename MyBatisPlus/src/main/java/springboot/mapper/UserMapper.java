package springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import springboot.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Description: TODO
 * CreateTime: 2022/4/26 14:14
 * @author: dxz
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
