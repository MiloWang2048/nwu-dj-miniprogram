package cn.milolab.dj.test.util;

import cn.milolab.dj.util.DateUtil;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilTest {
    @Test
    public void test() {
        Date d1 = new Date(1596129976000L);
        Date d2 = new Date(1596129976596L);
        Date d3 = new Date(1596129977000L);
        assertTrue("忽略小于1000ms的误差", DateUtil.equals(d1, d2));
        assertFalse("时间差大于1000ms时应判定为不同", DateUtil.equals(d1, d3));
    }
}
