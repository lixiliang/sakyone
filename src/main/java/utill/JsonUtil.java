package utill;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: JsonUtil <br/> 
 * @Describtion: JSON与Java Bean之间的转换. <br/> 
 * @date: 2018年4月17日 下午10:29:39 <br/> 
 * @author Beats <br/> 
 * @version v1.0
 */
public class JsonUtil {

     // 定义ObjectMapper对象，用于数据转换
    private static final ObjectMapper mapper = new ObjectMapper();
    
    /**
     * getJSON:(对象转换成JSON). <br/> 
     * @return String
     */
    public static String getJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * getBean:(JSON字符串转对象). <br/> 
     * @param json
     * @param t
     * @return T
     */
    public static <T> T getBean(String json, Class<T> t) {
        try {
            return mapper.readValue(json, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * jsonToObject:(JSON字符串转对象). <br/> 
     * @param json
     * @return T
     */
    public static <T> List<T> getBeanList(String json, Class<T> beanType) {
        try {
            //方案一：
//            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, beanType);
            //方案二：
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
            return mapper.readValue(json, javaType);
            //方案三
//            TypeReference<T> typeReference = new TypeReference<T>() {};
//            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    

}