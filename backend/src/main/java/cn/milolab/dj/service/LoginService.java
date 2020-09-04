package cn.milolab.dj.service;

import cn.milolab.dj.bean.business.WxLoginResult;
import cn.milolab.dj.bean.entity.Employee;
import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.bean.request.LoginRequest;
import cn.milolab.dj.bean.response.LoginResponse;
import cn.milolab.dj.conf.business.MiniprogramConfig;
import cn.milolab.dj.dao.EmployeeDAO;
import cn.milolab.dj.dao.UserDAO;
import cn.milolab.dj.error.exception.InternalServerErrorException;
import cn.milolab.dj.error.exception.UnauthenticatedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author milowang
 */
@Service
public class LoginService {

    private static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret" +
            "={appsecret}&js_code={jscode}&grant_type=authorization_code";

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MiniprogramConfig miniprogramConfig;

    @Autowired
    UserDAO userDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    /**
     * 小程序登录
     *
     * @param request 登录请求
     * @return 登录响应体
     */
    public LoginResponse wxLogin(LoginRequest request) {

        // 准备参数
        Map<String, String> requestParam = new HashMap<>(3);
        requestParam.put("appid", miniprogramConfig.getAppid());
        requestParam.put("appsecret", miniprogramConfig.getAppsecret());
        requestParam.put("jscode", request.getJscode());
        LOGGER.debug(requestParam);

        // 请求微信认证
        WxLoginResult result = requestWxAuth(requestParam);

        // 查找本地用户，没有则创建
        User user = userDAO.findByOpenid(result.getOpenid());
        if (user == null) {
            createAccount(result);
            user = userDAO.findByOpenid(result.getOpenid());
        }

        // shiro登录
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setTimeout(-1000L);
        subject.login(new UsernamePasswordToken(result.getOpenid(), ""));

        // 更新头像链接和session key
        if (request.getAvatarUrl() != null) {
            user.setAvatarUrl(request.getAvatarUrl());
        }
        user.setSessionKey(result.getSession_key());
        userDAO.updateWithEntity(user);

        // 获取社员信息
        Employee employee = employeeDAO.findByUserId(user.getId());
        LOGGER.debug(employee);
        LoginResponse response = new LoginResponse();
        if (employee != null) {
            BeanUtils.copyProperties(employee, response);
        }
        return response;
    }

    /**
     * 请求微信认证
     *
     * @param requestParam 请求参数map
     * @return 微信登录结果
     */
    private WxLoginResult requestWxAuth(Map<String, String> requestParam) {
        WxLoginResult result;
        try {
            result = restTemplate.getForObject(WX_LOGIN_URL, WxLoginResult.class, requestParam);
        } catch (RestClientException e) {
            LOGGER.warn(e.getStackTrace());
            throw new InternalServerErrorException("服务端网络错误");
        }
        if (result == null) {
            throw new InternalServerErrorException("登录逻辑异常");
        }
        if (result.getErrcode() != null && result.getErrcode() != 0) {
            LOGGER.warn(result);
            throw new UnauthenticatedException("登录口令无效");
        }
        return result;
    }

    /**
     * 根据微信登录结果创建用户
     *
     * @param result 微信登录结果
     */
    private void createAccount(WxLoginResult result) {

        // 生成用户实体并插入数据库
        User user = new User();
        user.setOpenid(result.getOpenid());
        user.setUnionid(result.getUnionid());
        int insertResult;
        insertResult = userDAO.insertOne(user);

        // 如果结果不为1，报500
        if (insertResult != 1) {
            throw new InternalServerErrorException("用户创建失败");
        }
    }
}
