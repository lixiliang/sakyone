package utill;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanMapUtils2 {
	/**
	 * Converts a map to a JavaBean.
	 *
	 * @param type type to convert
	 * @param map map to convert
	 * @return JavaBean converted
	 */
	public static <T> T mapToBean(Map<String, ? extends Object> map,Class<T> type){
		T obj = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			obj = type.newInstance();
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i< propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (Exception e) {
		}
		return obj;
	}

	/**
	 * Converts a JavaBean to a map.
	 *
	 * @param bean JavaBean to convert
	 * @return map converted
	 * */
	public static final Map<String, Object> beanToMap(Object bean){
		Map<String, Object> returnMap = new HashMap<>();
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i< propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
		} catch (Exception e) {
		}
		return returnMap;
	}
}
