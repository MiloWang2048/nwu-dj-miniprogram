package cn.milolab.dj.test.service;

import cn.milolab.dj.bean.request.AddJobRequest;
import cn.milolab.dj.bean.request.listing.PageRequest;
import cn.milolab.dj.bean.response.ListResponse;
import cn.milolab.dj.service.JobService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JobServiceTest {
    @Autowired
    private JobService jobService;

    @Test
    public void getAllActiveJobs() {
        PageRequest pageRequest = new PageRequest(1, 10);
        ListResponse listResponse = jobService.getAllActiveJobs(pageRequest);
        System.out.println(listResponse);
    }

    @Test
    public void getMyJobList() {
        PageRequest pageRequest = new PageRequest(1, 10);
        ListResponse listResponse = jobService.getMyJobList(pageRequest, 1);
        System.out.println(listResponse);
    }

    @Test
    public void addJob() {
        AddJobRequest request = new AddJobRequest("name", "position", new Date(), new Date(), 3);
        boolean res = jobService.addJob(request);
        Assert.assertTrue(res);
    }
}
