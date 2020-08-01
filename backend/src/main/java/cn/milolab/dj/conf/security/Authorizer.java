package cn.milolab.dj.conf.security;

import cn.milolab.dj.bean.entity.AdminInfo;
import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.dao.AdminInfoDAO;
import cn.milolab.dj.dao.UserDAO;
import cn.milolab.dj.error.exception.BadRequestExceptionBase;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 这个realm的bean名称必须为authorizer，否则无法启动security manager
 *
 * @author milowang
 */
@Component("authorizer")
public class Authorizer extends AuthorizingRealm {

    @Autowired
    UserDAO userDAO;

    @Autowired
    AdminInfoDAO adminInfoDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String openid = (String) principals.getPrimaryPrincipal();
        User user = userDAO.findByOpenid(openid);
        AdminInfo adminInfo = adminInfoDAO.findByUserId(user.getId());
        return new AuthorizationInfo() {
            @Override
            public Collection<String> getRoles() {
                List<String> roles = new ArrayList<>();
                if (adminInfo == null) {
                    return roles;
                }
                roles = Arrays.asList(adminInfo.getRole().split(" "));
                return roles;
            }

            @Override
            public Collection<String> getStringPermissions() {
                return null;
            }

            @Override
            public Collection<Permission> getObjectPermissions() {
                return null;
            }
        };
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String openid = (String) token.getPrincipal();
        User user = userDAO.findByOpenid(openid);
        if (user == null) {
            throw new BadRequestExceptionBase("用户不存在");
        }
        return new SimpleAuthenticationInfo(openid, "", this.getName());
    }
}
