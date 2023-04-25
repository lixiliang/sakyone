package utill;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String[] getNotNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> names = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue != null && !pd.getName().equals("class")) {
                names.add(pd.getName());
            }
        }
        String[] result = new String[names.size()];
        return names.toArray(result);
    }


    public static <T> T createInstance(Class<?> clazz) {
        try {
            if (clazz == null) {
                return null;
            }
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Error occur during creating instance of " + clazz, e);
        }
    }

    public static Object getField(Field field, Object instance) throws RuntimeException {
        try {
            if (!(field.isAccessible())) {
                field.setAccessible(true);
            }
            return field.get(instance);
        } catch (Exception e) {
            throw new RuntimeException("Error occur during getting field: " + field, e.getCause());
        }
    }
    /**
     * 根据属性名获取属性值
     * 支持继承层次
     * @param fieldName
     * @param object
     * @return
     */

    public static Object getFieldValue(String fieldName, Object object) {
        try {
            Field field = getField(fieldName,object);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            return null;
        }
    }


    public static Object invokeGetter(Object instance, String property) {
        Method getter;
        Class clazz = instance.getClass();
        try {
            getter = clazz.getMethod("get" + Character.toUpperCase(property.charAt(0)) + property.substring(1), new Class[0]);
        } catch (Exception e) {
            throw new RuntimeException("No getter method found: " + e, e);
        }
        return invokeMethod(getter, instance, new Object[0]);
    }

    public static void invokeSetter(Object instance, String property, Class<?> valueClass, Object value) {
        Method setter;
        Class clazz = instance.getClass();
        try {
            setter = clazz.getMethod("set" + Character.toUpperCase(property.charAt(0)) + property.substring(1), valueClass);
        } catch (Exception e) {
            throw new RuntimeException("No setter method found: " + e, e);
        }
        invokeMethod(setter, instance, new Object[]{value});
    }

    /**
     * 只在当前类中查找
     *
     * @param method
     * @param instance
     * @param parameters
     * @return
     * @throws RuntimeException
     */
    public static Object invokeMethod(Method method, Object instance, Object[] parameters) throws RuntimeException {
        try {
            return method.invoke(instance, parameters);
        } catch (Exception e) {
            throw new RuntimeException("Error occur during invoking method: " + method + " with parameters(" +
                    Arrays.asList(parameters)
                    + ")", e.getCause());
        }
    }

    public static void setField(Field field, Object instance, Object value) throws RuntimeException {
        try {
            if (!(field.isAccessible())) {
                field.setAccessible(true);
            }
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException("Error occur during setting field: " + field + " with value(" + value + ")", e
                    .getCause());
        }
    }

    /**
     * 支持继承层次获取
     *
     * @param clazz
     * @return
     */
    public static Field[] getAllFields(Class clazz) {
        List<Field> allFields = Lists.newArrayList();
        Class tempClass = clazz;
        //获取父类上的fields
        while (tempClass != null) {//当父类为null的时候说明到达了最上层的父类(Object类).
            allFields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        allFields = allFields.stream().distinct().collect(Collectors.toList());
        return allFields.toArray(new Field[allFields.size()]);
    }

    /**
     * 根据属性名获取属性值
     * 支持继承层次
     * @param fieldName
     * @param object
     * @return
     */

    public static Field getField(String fieldName, Object object) {
        try {
            Field field = null;
            Class clazz = object.getClass();
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                if (clazz.getSuperclass() == null) {
                    return null;
                } else {
                    field = getField(fieldName, clazz.getSuperclass());
                }
            }
            return field;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 支持递归查找继承类方法
     *
     * @param clazz
     * @param methodName
     * @param classes
     * @return
     * @throws Exception
     */
    public static Method getMethod(Class clazz, String methodName, final Class[] classes) throws Exception {
        Method method;
        try {
            method = clazz.getDeclaredMethod(methodName, classes);
        } catch (NoSuchMethodException e) {
            if (clazz.getSuperclass() == null) {
                return null;
            } else {
                method = getMethod(clazz.getSuperclass(), methodName, classes);
            }
        }
        return method;
    }


    /**
     * @param obj        调用方法的对象
     * @param methodName 方法名
     * @param classes    参数类型数组
     * @param objects    参数数组
     * @return 方法的返回值
     */
    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes, final Object[] objects) {
        try {
            Method method = getMethod(obj.getClass(), methodName, classes);
            method.setAccessible(true);// 调用private方法的关键一句话
            return method.invoke(obj, objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object invoke(final Object obj, final String methodName,
                                final Class[] classes) {
        return invoke(obj, methodName, classes, new Object[]{});
    }

    public static Object invoke(final Object obj, final String methodName) {
        return invoke(obj, methodName, new Class[]{}, new Object[]{});
    }
}