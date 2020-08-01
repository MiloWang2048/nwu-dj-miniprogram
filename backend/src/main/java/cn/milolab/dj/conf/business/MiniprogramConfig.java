package cn.milolab.dj.conf.business;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
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
