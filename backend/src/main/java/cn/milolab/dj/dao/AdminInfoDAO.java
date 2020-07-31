package cn.milolab.dj.dao;

import cn.milolab.dj.bean.entity.AdminInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author milowang
 */
@Repository
public interface AdminInfoDAO {
    /**
     * 返回所有管理员信息
     *
     * @return 所有管理员信息列表
     */
    List<AdminInfo> getAllAdminInfo();

    /**
     * 按id查找管理员信息
     *
     * @param id 要查找记录的id
     * @return 查找到的信息
     */
    AdminInfo findById(int id);

    /**
     * 添加一个管理员
     *
     * @param adminInfo 要添加的管理员信息
     * @return 受影响的行数
     */
    int insertOne(AdminInfo adminInfo);

    /**
     * 删除指定id的管理员
     *
     * @param id 要删除记录的id
     * @return 受影响的行数
     */
    int deleteById(int id);

    /**
     * 修改指定id的管理员
     *
     * @param adminInfo 修改过的新记录
     * @return 受影响的行数
     */
    int updateWithEntity(AdminInfo adminInfo);
}
