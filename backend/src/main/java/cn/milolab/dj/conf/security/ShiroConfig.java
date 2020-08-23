package cn.milolab.dj.conf.security;

import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author milowang
 */
@Configuration
public class ShiroConfig {
    @Value("${enableAccessControl}")
    private Boolean enableAccessControl;

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

        chainDefinition.addPathDefinition("/swagger-ui.html", "anon");
        chainDefinition.addPathDefinition("/webjars/springfox-swagger-ui/**", "anon");
        chainDefinition.addPathDefinition("/v2/api-docs", "anon");
        chainDefinition.addPathDefinition("/swagger-resources/**", "anon");

        if (enableAccessControl) {
            chainDefinition.addPathDefinition("/**", "restAuthFilter");
        } else {
            chainDefinition.addPathDefinition("/**", "anon");
        }
        return chainDefinition;
    }

}
