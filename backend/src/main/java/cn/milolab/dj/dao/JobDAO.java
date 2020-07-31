package cn.milolab.dj.dao;

import cn.milolab.dj.bean.entity.Job;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author milowang
 */
@Repository
public interface JobDAO {
    /**
     * 返回所有班次信息
     *
     * @return 所有班次信息列表
     */
    List<Job> getAllJobs();

    /**
     * 按id查找班次信息
     *
     * @param id 要查找班次的id
     * @return 查找到的实体
     */
    Job findById(int id);

    /**
     * 添加一个班次
     *
     * @param job 要添加的班次信息
     * @return 受影响的行数
     */
    int insertOne(Job job);

    /**
     * 删除指定id的班次
     *
     * @param id 要删除记录的id
     * @return 受影响的行数
     */
    int deleteById(int id);

    /**
     * 修改指定id的班次
     *
     * @param job 修改过的新记录
     * @return 受影响的行数
     */
    int updateWithEntity(Job job);
}
