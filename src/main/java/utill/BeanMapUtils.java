package utill;

import entity.PapOrderResult;
import org.springframework.cglib.beans.BeanMap;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.beans.BeanUtils.getPropertyDescriptors;
import static org.springframework.beans.BeanUtils.instantiateClass;

/**
 * 注意 bean 如果是用lombok 工具生成的，不可以加上@Accessors(chain = true)
 */
public class BeanMapUtils {
    public BeanMapUtils() {
    }
    /**
     * 实例化对象：传入类对类进行实例化对象
     *
     * @param clazz 类
     * @return 对象
     * @author Lius
     * @date 2018/10/26 13:49
     */
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiateClass(clazz);
    }

    /**
     * 实例化对象，传入类名对该类进行实例化对象
     *
     * @param clazzStr 类名,传入是必须传入全路径，com...
     * @return 对象
     * @author Lius
     * @date 2018/10/26 13:54
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 把对象封装成Map形式
     *
     * @param src 需要封装的实体对象
     * @author Lius
     * @date 2018/10/26 14:08
     */
    public static Map beanToMap(Object src) {
        return BeanMap.create(src);
    }

    /**
     * 把Map转换成bean对象
     *
     * @author Lius
     * @date 2018/10/26 14:09
     */
    public static <T> T mapToBean(Map<String, ? extends Object> beanMap, Class<T> valueType) {
        // 对象实例化
        T bean = BeanUtils.newInstance(valueType);
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(valueType);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String properName = propertyDescriptor.getName();
            // 过滤class属性
            if ("class".equals(properName)) {
                continue;
            }
            if (beanMap.containsKey(properName)) {
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (null == writeMethod) {
                    continue;
                }
                Object value = beanMap.get(properName);
                if (!writeMethod.isAccessible()) {
                    writeMethod.setAccessible(true);
                }
                try {
                    writeMethod.invoke(bean, value);
                } catch (Throwable throwable) {
                    throw new RuntimeException("Could not set property '" + properName + " ' to bean" + throwable);
                }
            }
        }
        return bean;
    }


    public static void main(String[] args) {
        PapOrderResult result = new PapOrderResult();
        Map<String, String> resp = new HashMap<>();
        resp.put("mch_id","1111");
        resp.put("out_trade_no","222");
        resp.put("trade_state","ACEPT");
        result = BeanMapUtils.mapToBean(resp, PapOrderResult.class);
        System.out.println(result);
        resp = BeanMapUtils.beanToMap(result);
        System.out.println(resp);
    }
}
