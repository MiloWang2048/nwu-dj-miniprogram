package cn.milolab.dj.dao;

import cn.milolab.dj.bean.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author milowang
 */
@Repository
public interface UserDAO {
    /**
     * 返回所有用户信息
     *
     * @return 所有用户信息列表
     */
    List<User> getAllUsers();

    /**
     * 按id查找用户信息
     *
     * @param id 要查找用户的id
     * @return 查找到的实体
     */
    User findById(int id);

    /**
     * 添加一个用户
     *
     * @param user 要添加的用户信息
     * @return 受影响的行数
     */
    int insertOne(User user);

    /**
     * 删除指定id的用户
     *
     * @param id 要删除记录的id
     * @return 受影响的行数
     */
    int deleteById(int id);

    /**
     * 修改指定id的用户
     *
     * @param user 修改过的新记录
     * @return 受影响的行数
     */
    int updateWithEntity(User user);

    /**
     * 按openid查找用户信息
     *
     * @param openid 用户openid
     * @return 用户信息实体
     */
    User findByOpenid(String openid);
}
