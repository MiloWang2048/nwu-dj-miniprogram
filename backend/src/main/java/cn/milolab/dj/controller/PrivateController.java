package cn.milolab.dj.controller;

import cn.milolab.dj.annotation.ManualRoleCheck;
import cn.milolab.dj.bean.entity.Employee;
import cn.milolab.dj.bean.entity.Job;
import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.bean.request.AddJobRequest;
import cn.milolab.dj.bean.request.ApplyJobRequest;
import cn.milolab.dj.bean.request.ExchangeJobRequest;
import cn.milolab.dj.bean.request.JobDetailRequest;
import cn.milolab.dj.bean.request.listing.PageRequest;
import cn.milolab.dj.bean.response.ListResponse;
import cn.milolab.dj.service.JobService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get_job_detail")
    @ManualRoleCheck("EMPLOYEE")
    public Job getJobDetail(@Validated JobDetailRequest jobDetailRequest, BindingResult bindingResult) {
        return jobService.getJobById(jobDetailRequest.getJobId());
    }

    @PostMapping("/add_job")
    @ManualRoleCheck("SECRETARY")
    public void addJob(@Validated @RequestBody AddJobRequest request, BindingResult result) {
        jobService.addJob(request);
    }

    @PostMapping("/apply_job")
    @ManualRoleCheck("EMPLOYEE")
    public void applyJob(@Validated @RequestBody ApplyJobRequest request, BindingResult result) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getSession().getAttribute("EmployeeEntity");
        jobService.applyJob(request, employee.getId());
    }

    @PostMapping("/exchange_job")
    @ManualRoleCheck("EMPLOYEE")
    public void exchangeJob(@Validated @RequestBody ExchangeJobRequest request, BindingResult result) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getSession().getAttribute("EmployeeEntity");
        jobService.exchangeJob(request, employee.getId());
    }
}
