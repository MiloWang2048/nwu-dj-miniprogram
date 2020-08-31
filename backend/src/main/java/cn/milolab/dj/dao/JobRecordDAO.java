package cn.milolab.dj.dao;

import cn.milolab.dj.bean.entity.JobRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author milowang
 */
@Repository
public interface JobRecordDAO {
    /**
     * 返回所有值班记录
     *
     * @return 所有值班记录列表
     */
    List<JobRecord> getAllJobRecords();

    /**
     * 按id查找值班记录
     *
     * @param id 要查找值班记录的id
     * @return 查找到的实体
     */
    JobRecord findById(int id);

    /**
     * 添加一个值班记录
     *
     * @param jobRecord 要添加的值班记录
     * @return 受影响的行数
     */
    int insertOne(JobRecord jobRecord);

    /**
     * 删除指定id的值班记录
     *
     * @param id 要删除记录的id
     * @return 受影响的行数
     */
    int deleteById(int id);

    /**
     * 修改指定id的值班记录
     *
     * @param jobRecord 修改过的新记录
     * @return 受影响的行数
     */
    int updateWithEntity(JobRecord jobRecord);

    /**
     * 获取指定用户的抢班记录
     * @param employeeId 用户id
     * @return 抢班记录列表
     */
    List<JobRecord> getMyJobsRecord(int employeeId);
}
