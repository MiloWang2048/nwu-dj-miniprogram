package cn.milolab.dj.service;

import cn.milolab.dj.bean.business.WxLoginResult;
import cn.milolab.dj.bean.entity.AdminInfo;
import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.bean.request.LoginRequest;
import cn.milolab.dj.bean.response.LoginResponse;
import cn.milolab.dj.conf.business.MiniprogramConfig;
import cn.milolab.dj.dao.AdminInfoDAO;
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
    AdminInfoDAO adminInfoDAO;

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
        // 获取管理员信息
        AdminInfo adminInfo = adminInfoDAO.findByUserId(user.getId());
        LOGGER.debug(adminInfo);
        LoginResponse response = new LoginResponse();
        if (adminInfo != null) {
            BeanUtils.copyProperties(adminInfo, response);
        }
        return response;
    }

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

    private void createAccount(WxLoginResult result) {
        User user = new User();
        user.setOpenid(result.getOpenid());
        user.setUnionid(result.getUnionid());
        int insertResult;
        try {
            insertResult = userDAO.insertOne(user);
        } catch (Exception e) {
            LOGGER.warn(result);
            LOGGER.warn(e);
            throw new InternalServerErrorException("用户创建失败");
        }
        if (insertResult != 1) {
            throw new InternalServerErrorException("用户创建失败");
        }
    }
}
