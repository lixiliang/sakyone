import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FieldMain {
    private static Logger logger = LoggerFactory.getLogger(FieldMain.class);


    public static void excluedField(Object object, List<String> excludeFields) {
        for (String field : excludeFields) {
            invokeSet(object, field, null);
        }
    }
    public static void includeField(Object object, List<String> includeFields) {
        List<String> fields = getAllField(object.getClass());
        for (String field : fields) {
            if (!includeFields.contains(field)) {
                invokeSet(object,field,null);
            }
        }
    }

    private static List<String> getAllField(Class clazz) {
        List<Field> allFields = Lists.newArrayList();
        //获取父类上的fields
        while (clazz != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
        }
        return allFields.stream().map(field -> field.getName()).collect(Collectors.toList());
    }

    /**
     * java反射bean的set方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getSetMethod(Class objectClass, String fieldName) {
        try {
            Class[] parameterTypes = new Class[1];
            Field field = null;
            while (objectClass != null) {
                try {
                    field = objectClass.getDeclaredField(fieldName);
                } catch (Exception e) { }
                if(field == null){
                    objectClass = objectClass.getSuperclass();
                }else {
                    break;
                }
            }
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);
            if(method == null){
                Class superClass = objectClass.getSuperclass();
                while (superClass != null){
                    method = superClass.getMethod(sb.toString(), parameterTypes);
                    if(method != null){
                        return method;
                    }else {
                        superClass = superClass.getSuperclass();
                    }
                }
            }
            return method;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * java反射bean的get方法
     *
     * @param objectClass
     * @param fieldName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method getGetMethod(Class objectClass, String fieldName) {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try {
            return objectClass.getMethod(sb.toString());
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 执行set方法
     *
     * @param o         执行对象
     * @param fieldName 属性
     * @param value     值
     */
    public static void invokeSet(Object o, String fieldName, Object value) {
        Method method = getSetMethod(o.getClass(), fieldName);
        try {
            method.invoke(o, new Object[]{value});
        } catch (Exception e) {
            logger.warn("error, fieldName:{}",fieldName);
        }
    }

    /**
     * 执行get方法
     *
     * @param o         执行对象
     * @param fieldName 属性
     */
    public static Object invokeGet(Object o, String fieldName) {
        Method method = getGetMethod(o.getClass(), fieldName);
        try {
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
