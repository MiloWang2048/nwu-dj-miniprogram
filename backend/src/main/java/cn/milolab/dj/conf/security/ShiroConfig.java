package cn.milolab.dj.conf.security;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author milowang
 */
@Configuration
public class ShiroConfig {

    @Autowired
    @Bean
    public FilterRegistrationBean<RestAuthFilter> restFilterRegistration(RestAuthFilter restAuthFilter) {
        FilterRegistrationBean<RestAuthFilter> registration = new FilterRegistrationBean<>(restAuthFilter);
        // must be false, or will be conflict with original filter
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/api/public/**", "anon");
        chainDefinition.addPathDefinition("/**", "restAuthFilter");
        return chainDefinition;
    }

}
