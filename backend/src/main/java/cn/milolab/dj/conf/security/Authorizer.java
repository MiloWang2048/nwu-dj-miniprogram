package cn.milolab.dj.conf.security;

import cn.milolab.dj.bean.entity.Employee;
import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.dao.EmployeeDAO;
import cn.milolab.dj.dao.UserDAO;
import cn.milolab.dj.error.exception.BadRequestException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
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
    EmployeeDAO employeeDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String openid = (String) principals.getPrimaryPrincipal();
        User user = userDAO.findByOpenid(openid);
        Employee employee = employeeDAO.findByUserId(user.getId());
        return new AuthorizationInfo() {
            @Override
            public Collection<String> getRoles() {
                List<String> roles = new ArrayList<>();
                roles.add("USER");
                if (employee == null) {
                    return roles;
                }
                roles = Arrays.asList(employee.getRole().split(" "));
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
            throw new BadRequestException("用户不存在");
        }
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("UserEntity", user);
        return new SimpleAuthenticationInfo(openid, "", this.getName());
    }
}
