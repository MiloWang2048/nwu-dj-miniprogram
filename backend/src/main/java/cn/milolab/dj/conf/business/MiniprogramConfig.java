package cn.milolab.dj.conf.business;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 小程序配置类
 *
 * @author milowang
 */
@Component
@Data
public class MiniprogramConfig {
    @Value("${miniprogram.appid}")
    private String appid;

    @Value("${miniprogram.appsecret}")
    private String appsecret;
}
