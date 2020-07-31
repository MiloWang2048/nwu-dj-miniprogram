package cn.milolab.dj.test.dao;

import cn.milolab.dj.bean.entity.ExchangeRecord;
import cn.milolab.dj.dao.ExchangeRecordDAO;
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
public class ExchangeRecordDAOTest {
    @Autowired
    ExchangeRecordDAO exchangeRecordDAO;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void before() {
        jdbcTemplate.execute("delete from exchange_record");
        jdbcTemplate.execute("alter table exchange_record auto_increment = 1");
    }

    @After
    public void after() {
        jdbcTemplate.execute("delete from exchange_record");
        jdbcTemplate.execute("alter table exchange_record auto_increment = 1");
    }

    @Test
    public void curdTest() throws InterruptedException {
        ExchangeRecord rawData = new ExchangeRecord(null, null, null, null, 1,
                2, 3, false);
        // insert
        int insertResult = exchangeRecordDAO.insertOne(rawData);
        assertEquals("数据插入失败", 1, insertResult);
        // find
        ExchangeRecord findResult = exchangeRecordDAO.findById(1);
        rawData.setId(findResult.getId());
        rawData.setDeleted(findResult.getDeleted());
        rawData.setCstCreate(findResult.getCstCreate());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("查询数据与原数据不一致", rawData, findResult);
        // list
        List<ExchangeRecord> listResult = exchangeRecordDAO.getAllExchangeRecords();
        assertArrayEquals("列表数据与原数据不一致", new ExchangeRecord[]{rawData}, listResult.toArray());
        // update
        Thread.sleep(1000);
        rawData.setTargetUserId(10);
        int updateResult = exchangeRecordDAO.updateWithEntity(rawData);
        assertEquals("update影响行数必须为1", 1, updateResult);
        findResult = exchangeRecordDAO.findById(1);
        assertNotEquals("update后修改时间应变动", rawData.getCstModified().getTime(), findResult.getCstModified().getTime());
        rawData.setCstModified(findResult.getCstModified());
        assertEquals("修改未生效", rawData, findResult);
        // delete
        int deleteResult = exchangeRecordDAO.deleteById(1);
        assertEquals("delete影响行数必须为1", 1, deleteResult);
        findResult = exchangeRecordDAO.findById(1);
        assertNull(findResult);
    }
}
