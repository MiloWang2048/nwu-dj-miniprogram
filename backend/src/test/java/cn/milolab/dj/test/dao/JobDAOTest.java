package cn.milolab.dj.test.dao;

import cn.milolab.dj.bean.entity.Job;
import cn.milolab.dj.dao.JobDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JobDAOTest {
    @Autowired
    JobDAO jobDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        jdbcTemplate.execute("delete from job");
        jdbcTemplate.execute("alter table job auto_increment = 1");
    }

    @After
    public void after() {
        jdbcTemplate.execute("delete from job");
        jdbcTemplate.execute("alter table job auto_increment = 1");
    }

    @Test
    public void curdTest() throws InterruptedException {
        Job rawData = new Job(null, null, null, null, "test",
                "test", new Date(), new Date(), 2);
        // insert
        int insertResult = jobDAO.insertOne(rawData);
        assertEquals("数据插入失败", 1, insertResult);
        // find
        Job findResult = jobDAO.findById(1);
        rawData.setId(findResult.getId());
        rawData.setDeleted(findResult.getDeleted());
        rawData.setCstCreate(findResult.getCstCreate());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("查询数据与原数据不一致", rawData, findResult);
        // list
        List<Job> listResult = jobDAO.getAllJobs();
        assertArrayEquals("列表数据与原数据不一致", new Job[]{rawData}, listResult.toArray());
        // update
        Thread.sleep(1000);
        rawData.setJobPosition("modified");
        int updateResult = jobDAO.updateWithEntity(rawData);
        assertEquals("update影响行数必须为1", 1, updateResult);
        findResult = jobDAO.findById(1);
        assertNotEquals("update后修改时间应变动", rawData.getCstModified().getTime(), findResult.getCstModified().getTime());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("修改未生效", rawData, findResult);
        // delete
        int deleteResult = jobDAO.deleteById(1);
        assertEquals("delete影响行数必须为1", 1, deleteResult);
        findResult = jobDAO.findById(1);
        assertNull(findResult);
    }
}
