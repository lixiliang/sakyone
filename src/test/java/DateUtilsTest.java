import lombok.extern.slf4j.Slf4j;
import utill.DateUtils;

import java.util.Date;

/**
 * Created by admin on 2017/9/13.
 */
@Slf4j
public class DateUtilsTest {


    @org.junit.Test
    public void test01() {
        Date d1 = DateUtils.stringToDate("2020-09-24 00:00:00");
        Date d2 = DateUtils.stringToDate("2020-09-23 23:59:59");
        int result = DateUtils.compareDays(d1,d2);
        log.info("result:{}",result);
    }


}

