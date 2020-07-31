package cn.milolab.dj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author milowang
 */
@SpringBootApplication
@MapperScan("cn.milolab.dj.dao")
public class DjBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(DjBackApplication.class, args);
    }
}
