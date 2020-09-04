package cn.milolab.dj.service;

import cn.milolab.dj.bean.entity.ExchangeRecord;
import cn.milolab.dj.bean.entity.Job;
import cn.milolab.dj.bean.entity.JobRecord;
import cn.milolab.dj.bean.request.AddJobRequest;
import cn.milolab.dj.bean.request.ApplyJobRequest;
import cn.milolab.dj.bean.request.ExchangeJobRequest;
import cn.milolab.dj.bean.request.listing.PageRequest;
import cn.milolab.dj.bean.response.ListResponse;
import cn.milolab.dj.dao.ExchangeRecordDAO;
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

    @Autowired
    ExchangeRecordDAO exchangeRecordDAO;

    /**
     * 获取所有活动班次
     *
     * @param pageRequest 分页请求
     * @return 列表响应
     */
    public ListResponse getAllActiveJobs(PageRequest pageRequest) {

        // 创建响应实体
        ListResponse response = new ListResponse();

        // 分页查询
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getSize());
        List<Job> activeJobs = jobDAO.getAllActiveJobs();
        PageInfo<Job> pageInfo = new PageInfo<>(activeJobs);

        // 填充响应实体
        response.setTotalPages(pageInfo.getPages());
        response.setList(activeJobs);

        return response;
    }

    /**
     * 获取社员抢班列表
     *
     * @param pageRequest 分页请求
     * @param employeeId  社员id
     * @return 列表响应实体
     */
    public ListResponse getMyJobList(PageRequest pageRequest, int employeeId) {

        // 创建响应实体
        ListResponse response = new ListResponse();

        // 分页查询社员值班列表
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getSize());
        List<JobRecord> myJobRecords = jobRecordDAO.getMyJobsRecord(employeeId);
        PageInfo<JobRecord> pageInfo = new PageInfo<>(myJobRecords);

        // 填充响应实体
        response.setList(myJobRecords);
        response.setTotalPages(pageInfo.getPages());

        return response;
    }

    /**
     * 获取排班详细信息
     *
     * @param jobId 班次id
     * @return 班次实体
     */
    public Job getJobById(int jobId) {
        return jobDAO.findById(jobId);
    }

    /**
     * 添加班次
     *
     * @param request 添加班次请求实体
     */
    public void addJob(AddJobRequest request) {

        // 创建班次实体
        Job job = new Job();

        // 拷贝属性到排班实体
        BeanUtils.copyProperties(request, job);

        // 插入数据
        int res = jobDAO.insertOne(job);

        // 如果影响行数不为1，报错
        if (res != 1) {
            throw new InternalServerErrorException("无法插入排班");
        }
    }

    /**
     * 判断一个社员是否能抢指定班次
     *
     * @param employeeId 社员id
     * @param jobId      班次id
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return 判断结果
     */
    boolean canApplyJob(int employeeId, int jobId, Date startTime, Date endTime) {

        // 查找当前班次的抢班记录和班次实体
        List<JobRecord> records = jobRecordDAO.getRecordsForJob(jobId);
        Job job = jobDAO.findById(jobId);

        // 起始和结束时间list，用于归并
        ArrayList<Date> startTimes = new ArrayList<>(), endTimes = new ArrayList<>();

        boolean applied = false;

        // 遍历当前班次的所有抢班记录
        for (JobRecord record : records) {

            // 如果这个记录不在要判断的时间段之内
            if (record.getStartTime().after(endTime) ||
                    record.getEndTime().before(startTime)) {

                // 忽略这条记录
                records.remove(record);
                continue;
            }

            // 添加起始时间和结束时间到两个归并列表中
            startTimes.add(record.getStartTime());
            endTimes.add(record.getEndTime());

            // 如果用户已经抢过这个班，退出
            if (record.getEmployeeId() == employeeId) {
                applied = true;
                break;
            }
        }
        if (applied) {
            return false;
        }

        // 如果达到最大值班人数，则不能抢班
        // 使用栈结构来计算最大值班人数

        // 首先对两个队列排序
        startTimes.sort(DateUtil.comparator);
        endTimes.sort(DateUtil.comparator);

        // current表示当前值班人数，max表示最大值班人数
        int current = 0, max = 0;

        // 当两个队列都不为空时
        while (!startTimes.isEmpty() && !endTimes.isEmpty()) {

            // 如果startTimes的第一个元素最早
            if (startTimes.get(0).before(endTimes.get(0))) {

                // 进栈，当前人数加一
                current++;

                // 摘除这条记录
                startTimes.remove(0);
            } else {

                // 如果sendTimes的第一个元素最早
                // 出栈，当前人数减一
                current--;

                // 摘除当前记录
                endTimes.remove(0);
            }

            // 记录最大值班人数
            if (current > max) {
                max = current;
            }
        }
        return job.getMaxJob() > max;
    }

    /**
     * 发起抢班申请
     *
     * @param request    抢班请求
     * @param employeeId 社员id
     */
    public void applyJob(ApplyJobRequest request, int employeeId) {

        // 如果社员可以抢班
        if (canApplyJob(employeeId, request.getJobId(), request.getStartTime(), request.getEndTime())) {

            // 创建抢班记录，填充属性，插入记录
            JobRecord jobRecord = new JobRecord();
            BeanUtils.copyProperties(request, jobRecord);
            jobRecord.setPresent(false);
            int result = jobRecordDAO.insertOne(jobRecord);
            if (result != 1) {
                throw new InternalServerErrorException("数据库错误");
            }
        } else {
            throw new BadRequestException("已抢或超出限制");
        }
    }

    /**
     * 处理换班请求
     * @param request 换班请求实体
     * @param originalEmployeeId 发起人的工号
     */
    public void exchangeJob(ExchangeJobRequest request, int originalEmployeeId) {

        // 判断这人有没有抢原来的班，如果没有抢班就不能换
        List<JobRecord> jobRecords = jobRecordDAO.getRecordsForJob(request.getJobId());
        JobRecord originalRecord = null;
        for (JobRecord record : jobRecords) {
            if (record.getEmployeeId() == originalEmployeeId) {
                originalRecord = record;
                break;
            }
        }
        if (originalRecord == null) {
            throw new BadRequestException("当前未抢班");
        }

        // 如果目标社员不能申请目标班次，则不能换班
        if (!canApplyJob(request.getTargetEmployeeId(),
                request.getJobId(),
                originalRecord.getStartTime(),
                originalRecord.getEndTime())) {
            throw new BadRequestException("目标社员非空闲");
        }

        // 修改值班信息
        originalRecord.setEmployeeId(request.getTargetEmployeeId());
        jobRecordDAO.deleteById(originalRecord.getId());
        jobRecordDAO.insertOne(originalRecord);

        //记录换班记录
        ExchangeRecord exchangeRecord = new ExchangeRecord();
        exchangeRecord.setAccepted(true);
        exchangeRecord.setJobId(request.getJobId());
        exchangeRecord.setOriginalEmployeeId(originalEmployeeId);
        exchangeRecord.setTargetEmployeeId(request.getTargetEmployeeId());
        exchangeRecordDAO.insertOne(exchangeRecord);
    }
}
