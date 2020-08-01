package cn.milolab.dj.controller;

import cn.milolab.dj.bean.request.LoginRequest;
import cn.milolab.dj.bean.response.LoginResponse;
import cn.milolab.dj.error.exception.BadRequestExceptionBase;
import cn.milolab.dj.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author milowang
 */
@RestController
@RequestMapping("/api/public")
public class LoginController {
    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestExceptionBase(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return loginService.wxLogin(request);
    }
}
