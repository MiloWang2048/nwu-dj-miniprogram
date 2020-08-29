package cn.milolab.dj.controller;

import cn.milolab.dj.annotation.ManualRoleCheck;
import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.bean.request.listing.PageRequest;
import cn.milolab.dj.bean.response.ListResponse;
import cn.milolab.dj.service.JobService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author milowang
 */
@RestController
@RequestMapping("/api/private")
public class PrivateController {
    @Autowired
    JobService jobService;

    @GetMapping("/get_active_job_list")
    @ManualRoleCheck("EMPLOYEE")
    public ListResponse getActiveJobList(@Validated PageRequest pageRequest, BindingResult bindingResult) {
        return jobService.getAllActiveJobs(pageRequest);
    }

    @GetMapping("/get_my_job_list")
    @ManualRoleCheck("EMPLOYEE")
    public ListResponse getMyJobList(@Validated PageRequest pageRequest, BindingResult bindingResult) {
        Subject subject = SecurityUtils.getSubject();
        int userId = ((User) subject.getSession().getAttribute("UserEntity")).getId();
        return jobService.getMyJobList(pageRequest, userId);
    }
}
