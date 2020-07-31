package cn.milolab.dj.bean.entity;

import cn.milolab.dj.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private String openid;
    private String unionid;
    private String sessionKey;
    private String avatarUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(deleted, user.deleted) &&
                DateUtil.equals(cstCreate, user.cstCreate) &&
                DateUtil.equals(cstModified, user.cstModified) &&
                Objects.equals(openid, user.openid) &&
                Objects.equals(unionid, user.unionid) &&
                Objects.equals(sessionKey, user.sessionKey) &&
                Objects.equals(avatarUrl, user.avatarUrl);
    }
}
