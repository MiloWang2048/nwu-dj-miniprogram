package cn.milolab.dj.util;

import java.util.Date;

/**
 * Date工具操作封装
 *
 * @author milowang
 */
public class DateUtil {
    /**
     * 比较Date，忽略毫秒
     *
     * @param o1 要比较的第一个Date
     * @param o2 要比较的第二个Date
     * @return 是否相等
     */
    public static boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        if (!(o1 instanceof Date) || !(o2 instanceof Date)) {
            return false;
        }
        Date d1 = (Date) o1;
        Date d2 = (Date) o2;
        return d1.getTime() / 1000 == d2.getTime() / 1000;
    }
}
