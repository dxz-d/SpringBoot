package springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description: TODO
 * CreateTime: 2022/4/26 13:59
 * @author: dxz
 */
@SpringBootApplication
@MapperScan("springboot.mapper")
public class MyBatisPlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusApplication.class);
    }
}
