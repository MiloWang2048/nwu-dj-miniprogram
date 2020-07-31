package cn.milolab.dj.bean.entity;

import cn.milolab.dj.util.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author milowang
 */
public class User {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private String openid;
    private String unionid;
    private String sessionKey;
    private String avatarUrl;

    public User() {
    }

    public User(Integer id, Boolean deleted, Date cstCreate, Date cstModified, String openid, String unionid, String sessionKey, String avatarUrl) {
        this.id = id;
        this.deleted = deleted;
        this.cstCreate = cstCreate;
        this.cstModified = cstModified;
        this.openid = openid;
        this.unionid = unionid;
        this.sessionKey = sessionKey;
        this.avatarUrl = avatarUrl;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, cstCreate, cstModified, openid, unionid, sessionKey, avatarUrl);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", cstCreate=" + cstCreate +
                ", cstModified=" + cstModified +
                ", openid='" + openid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCstCreate() {
        return cstCreate;
    }

    public void setCstCreate(Date cstCreate) {
        this.cstCreate = cstCreate;
    }

    public Date getCstModified() {
        return cstModified;
    }

    public void setCstModified(Date cstModified) {
        this.cstModified = cstModified;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
