package cn.milolab.dj.bean.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String name;
    private String jobSerial;
    private String role;
}
