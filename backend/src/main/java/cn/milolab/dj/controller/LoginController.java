package cn.milolab.dj.controller;

import cn.milolab.dj.bean.request.LoginRequest;
import cn.milolab.dj.bean.request.listing.PageRequest;
import cn.milolab.dj.bean.response.ListResponse;
import cn.milolab.dj.bean.response.LoginResponse;
import cn.milolab.dj.error.exception.BadRequestException;
import cn.milolab.dj.service.JobService;
import cn.milolab.dj.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author milowang
 */
@RestController
@RequestMapping("/api/public")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request, BindingResult bindingResult) {
        return loginService.wxLogin(request);
    }
}
