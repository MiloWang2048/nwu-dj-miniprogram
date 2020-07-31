package cn.milolab.dj.dao;

import cn.milolab.dj.bean.entity.ExchangeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author milowang
 */
@Repository
public interface ExchangeRecordDAO {
    /**
     * 返回所有换班记录
     *
     * @return 所有换班记录列表
     */
    List<ExchangeRecord> getAllExchangeRecords();

    /**
     * 按id查找换班记录
     *
     * @param id 要查找换班记录的id
     * @return 查找到的实体
     */
    ExchangeRecord findById(int id);

    /**
     * 添加一个换班记录
     *
     * @param exchangeRecord 要添加的换班记录
     * @return 受影响的行数
     */
    int insertOne(ExchangeRecord exchangeRecord);

    /**
     * 删除指定id的换班记录
     *
     * @param id 要删除记录的id
     * @return 受影响的行数
     */
    int deleteById(int id);

    /**
     * 修改指定id的换班记录
     *
     * @param exchangeRecord 修改过的新记录
     * @return 受影响的行数
     */
    int updateWithEntity(ExchangeRecord exchangeRecord);
}
