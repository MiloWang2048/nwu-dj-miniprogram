package cn.milolab.dj.service;

import cn.milolab.dj.bean.entity.Job;
import cn.milolab.dj.bean.entity.JobRecord;
import cn.milolab.dj.bean.request.listing.PageRequest;
import cn.milolab.dj.bean.response.ListResponse;
import cn.milolab.dj.dao.JobDAO;
import cn.milolab.dj.dao.JobRecordDAO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author milowang
 */
@Service
public class JobService {
    @Autowired
    JobDAO jobDAO;

    @Autowired
    JobRecordDAO jobRecordDAO;

    public ListResponse getAllActiveJobs(PageRequest pageRequest){
        ListResponse response = new ListResponse();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getSize());
        List<Job> activeJobs = jobDAO.getAllActiveJobs();
        PageInfo pageInfo = new PageInfo<>(activeJobs);
        response.setTotalPages(pageInfo.getPages());
        response.setList(activeJobs);
        return response;
    }

    public ListResponse getMyJobList(PageRequest pageRequest, int userId){
        ListResponse response = new ListResponse();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getSize());
        List<JobRecord> myJobRecords = jobRecordDAO.getMyJobsRecord(userId);
        PageInfo pageInfo = new PageInfo<>(myJobRecords);
        response.setList(myJobRecords);
        response.setTotalPages(pageInfo.getPages());
        return response;
    }

    public Job getJobById(int jobId){
        return jobDAO.findById(jobId);
    }
}
