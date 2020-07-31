package cn.milolab.dj.test.dao;

import cn.milolab.dj.bean.entity.JobRecord;
import cn.milolab.dj.dao.JobRecordDAO;
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
public class JobRecordDAOTest {
    @Autowired
    JobRecordDAO jobRecordDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        jdbcTemplate.execute("delete from job_record");
        jdbcTemplate.execute("alter table job_record auto_increment = 1");
    }

    @After
    public void after() {
        jdbcTemplate.execute("delete from job_record");
        jdbcTemplate.execute("alter table job_record auto_increment = 1");
    }

    @Test
    public void curdTest() throws InterruptedException {
        JobRecord rawData = new JobRecord(null, null, null, null, 1,
                2, false, new Date(), new Date());
        // insert
        int insertResult = jobRecordDAO.insertOne(rawData);
        assertEquals("数据插入失败", 1, insertResult);
        // find
        JobRecord findResult = jobRecordDAO.findById(1);
        rawData.setId(findResult.getId());
        rawData.setDeleted(findResult.getDeleted());
        rawData.setCstCreate(findResult.getCstCreate());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("查询数据与原数据不一致", rawData, findResult);
        // list
        List<JobRecord> listResult = jobRecordDAO.getAllJobRecords();
        assertArrayEquals("列表数据与原数据不一致", new JobRecord[]{rawData}, listResult.toArray());
        // update
        Thread.sleep(1000);
        rawData.setPresent(true);
        int updateResult = jobRecordDAO.updateWithEntity(rawData);
        assertEquals("update影响行数必须为1", 1, updateResult);
        findResult = jobRecordDAO.findById(1);
        assertNotEquals("update后修改时间应变动", rawData.getCstModified().getTime(), findResult.getCstModified().getTime());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("修改未生效", rawData, findResult);
        // delete
        int deleteResult = jobRecordDAO.deleteById(1);
        assertEquals("delete影响行数必须为1", 1, deleteResult);
        findResult = jobRecordDAO.findById(1);
        assertNull(findResult);
    }
}
