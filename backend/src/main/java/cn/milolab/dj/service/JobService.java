package cn.milolab.dj.service;

import cn.milolab.dj.bean.entity.Job;
import cn.milolab.dj.bean.entity.JobRecord;
import cn.milolab.dj.bean.request.AddJobRequest;
import cn.milolab.dj.bean.request.ApplyJobRequest;
import cn.milolab.dj.bean.request.listing.PageRequest;
import cn.milolab.dj.bean.response.ListResponse;
import cn.milolab.dj.dao.JobDAO;
import cn.milolab.dj.dao.JobRecordDAO;
import cn.milolab.dj.error.exception.BadRequestException;
import cn.milolab.dj.error.exception.InternalServerErrorException;
import cn.milolab.dj.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public ListResponse getAllActiveJobs(PageRequest pageRequest) {
        ListResponse response = new ListResponse();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getSize());
        List<Job> activeJobs = jobDAO.getAllActiveJobs();
        PageInfo<Job> pageInfo = new PageInfo<>(activeJobs);
        response.setTotalPages(pageInfo.getPages());
        response.setList(activeJobs);
        return response;
    }

    public ListResponse getMyJobList(PageRequest pageRequest, int userId) {
        ListResponse response = new ListResponse();
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getSize());
        List<JobRecord> myJobRecords = jobRecordDAO.getMyJobsRecord(userId);
        PageInfo<JobRecord> pageInfo = new PageInfo<>(myJobRecords);
        response.setList(myJobRecords);
        response.setTotalPages(pageInfo.getPages());
        return response;
    }

    public Job getJobById(int jobId) {
        return jobDAO.findById(jobId);
    }

    public void addJob(AddJobRequest request) {
        Job job = new Job();
        BeanUtils.copyProperties(request, job);
        int res = jobDAO.insertOne(job);
        if (res != 1) {
            throw new InternalServerErrorException("无法插入排班");
        }
    }

    boolean canApplyJob(int employeeId, ApplyJobRequest request) {
        List<JobRecord> records = jobRecordDAO.getRecordsForJob(request.getJobId());
        Job job = jobDAO.findById(request.getJobId());
        ArrayList<Date> startTimes = new ArrayList<>(), endTimes = new ArrayList<>();
        // 如果他已经抢班成功，则不能重复抢班
        boolean applied = false;
        for (JobRecord record : records) {
            if (record.getStartTime().after(request.getEndTime()) ||
                    record.getEndTime().before(request.getStartTime())) {
                records.remove(record);
                continue;
            }
            startTimes.add(record.getStartTime());
            endTimes.add(record.getEndTime());
            if (record.getEmployeeId() == employeeId) {
                applied = true;
            }
        }
        if (applied) {
            return false;
        }
        // 如果达到最大值班人数，则不能抢班
        startTimes.sort(DateUtil.comparator);
        endTimes.sort(DateUtil.comparator);
        int current = 0, max = 0;
        while (!startTimes.isEmpty() && !endTimes.isEmpty()) {
            if (startTimes.get(0).before(endTimes.get(0))) {
                current++;
                startTimes.remove(0);
            }else{
                current--;
                endTimes.remove(0);
            }
            if(current > max){
                max = current;
            }
        }
        return job.getMaxJob() > max;
    }

    public void applyJob(ApplyJobRequest request, int employeeId){
        if(canApplyJob(employeeId, request)){
            JobRecord jobRecord = new JobRecord();
            BeanUtils.copyProperties(request, jobRecord);
            jobRecord.setPresent(false);
            jobRecordDAO.insertOne(jobRecord);
        }else{
            throw new BadRequestException("已抢或超出限制");
        }
    }
}
