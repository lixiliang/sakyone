import com.google.common.base.CaseFormat;
import entity.VodInfoPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import utill.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author lixiliang
 * @describe
 * @date 2023/4/17
 */
@Slf4j
public class TestCase {
    public final static List<String> vodInfoFieldNames = new ArrayList<>();

    static {
        Field[] fields = ReflectUtils.getAllFields(VodInfoPo.class);
        for (Field field : fields) {
            vodInfoFieldNames.add(field.getName());
        }
    }
    @Test
    public void  t1(){
        int count = 10;
        List<Integer> numbers = IntStream.rangeClosed(1, count).boxed().collect(Collectors.toList());
        System.out.println(numbers); // 输出：[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        int BATCH_SIZE = 4;
        //分批查询
        int size = numbers.size();
        int loop = size / BATCH_SIZE + (size % BATCH_SIZE != 0 ? 1 : 0);
        for (int i = 0; i < loop; i++) {
            int endIndex = Math.min(size, BATCH_SIZE * (i + 1));
            List<Integer> subIds = numbers.subList(i * BATCH_SIZE, endIndex);
            log.info(" i:{},subIds:{},endIndex：{}",i,subIds,endIndex);
        }

    }
    @Test
    public  void vodFieldToColumn() {
        String columns = "*";
        List<String> fields = Arrays.asList("uuid", "name", "side_Image", "downImage", "defPlayNum", "vodType", "isVip", "cornerImage", "totalNum");
//        List<String> fields = Arrays.asList(" ", "null", "");
        if (!CollectionUtils.isEmpty(fields)) {
            //过滤掉不支持的字段
            String cols = fields.stream().filter(f -> {
                if(StringUtils.isBlank(f)){
                    return false;
                }
                String unify = f;
                if(containUnderscore(unify)){
                    unify  = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL , f);
                }
                return vodInfoFieldNames.contains(unify.trim());
            }).map(f ->{
                if(!containUnderscore(f)){
                    f = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f);
                }
                return f;
            }).collect(Collectors.joining(","));
            if(StringUtils.isNotBlank(cols)){
                columns = cols;
            }
        }
        log.info("{}",columns);
    }
    private boolean containUnderscore(String word){
        if(StringUtils.isNotBlank(word)){
            return word.indexOf('_')>=0;
        }
        return false;
    }
    private static String convertToColumn(List<String> validFields) {
        if(CollectionUtils.isEmpty(validFields)){
            return "*";
        }
//        return validFields.stream().map(f -> StringHelper.charToUnderLine(f)).collect(Collectors.joining(","));
        return validFields.stream().map(f ->CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f)).collect(Collectors.joining(","));
    }
}
