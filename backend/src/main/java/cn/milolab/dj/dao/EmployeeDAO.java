package cn.milolab.dj.dao;

import cn.milolab.dj.bean.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author milowang
 */
@Repository
public interface EmployeeDAO {
    /**
     * 返回所有社员信息
     *
     * @return 所有社员信息列表
     */
    List<Employee> getAllEmployees();

    /**
     * 按id查找社员信息
     *
     * @param id 要查找记录的id
     * @return 查找到的信息
     */
    Employee findById(int id);

    /**
     * 按user id查找社员信息
     *
     * @param userId 要查找社员的userId
     * @return 查找到的信息
     */
    Employee findByUserId(int userId);

    /**
     * 添加一个社员
     *
     * @param employee 要添加的社员信息
     * @return 受影响的行数
     */
    int insertOne(Employee employee);

    /**
     * 删除指定id的社员
     *
     * @param id 要删除记录的id
     * @return 受影响的行数
     */
    int deleteById(int id);

    /**
     * 修改指定id的社员
     *
     * @param employee 修改过的新记录
     * @return 受影响的行数
     */
    int updateWithEntity(Employee employee);

    /**
     * 根据学号查找社员
     *
     * @param stuSerial 要查找社员的学号
     * @return 查找到的社员实体
     */
    Employee findByStuSerial(String stuSerial);
}
