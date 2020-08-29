package cn.milolab.dj.test.setup;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateConfig {
    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }
}
