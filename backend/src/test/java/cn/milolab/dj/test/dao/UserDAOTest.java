package cn.milolab.dj.test.dao;

import cn.milolab.dj.bean.entity.User;
import cn.milolab.dj.dao.UserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDAOTest {
    @Autowired
    UserDAO userDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        jdbcTemplate.execute("delete from user");
        jdbcTemplate.execute("alter table user auto_increment = 1");
    }

    @After
    public void after() {
        jdbcTemplate.execute("delete from user");
        jdbcTemplate.execute("alter table user auto_increment = 1");
    }

    @Test
    public void curdTest() throws InterruptedException {
        User rawData = new User(null, null, null, null, "test", "test", "test", "test");
        // insert
        int insertResult = userDAO.insertOne(rawData);
        assertEquals("数据插入失败", 1, insertResult);
        // find
        User findResult = userDAO.findById(1);
        rawData.setId(findResult.getId());
        rawData.setDeleted(findResult.getDeleted());
        rawData.setCstCreate(findResult.getCstCreate());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("查询数据与原数据不一致", rawData, findResult);
        // list
        List<User> listResult = userDAO.getAllUsers();
        assertArrayEquals("列表数据与原数据不一致", new User[]{rawData}, listResult.toArray());
        // update
        Thread.sleep(1000);
        rawData.setAvatarUrl("modified");
        int updateResult = userDAO.updateWithEntity(rawData);
        assertEquals("update影响行数必须为1", 1, updateResult);
        findResult = userDAO.findById(1);
        assertNotEquals("update后修改时间应变动", rawData.getCstModified().getTime(), findResult.getCstModified().getTime());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("修改未生效", rawData, findResult);
        // delete
        int deleteResult = userDAO.deleteById(1);
        assertEquals("delete影响行数必须为1", 1, deleteResult);
        findResult = userDAO.findById(1);
        assertNull(findResult);
    }
}
