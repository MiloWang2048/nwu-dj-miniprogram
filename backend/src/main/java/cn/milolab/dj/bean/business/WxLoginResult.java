package cn.milolab.dj.bean.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxLoginResult {
    private String openid;
    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
