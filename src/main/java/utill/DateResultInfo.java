package utill;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName DateResultInfo
 * @Author ja
 * @Date 2019-12-11 17:59
 * @Description TODO
 **/
@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DateResultInfo {
    Integer year;
    Integer month;
    Integer week;
    Date date;
    Long time;
}
