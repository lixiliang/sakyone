package enums;

import java.util.Map;

/**
 * 枚举基类
 */
public interface BaseEnum {
    Map<Integer, String>  toMap();
    Integer getValue();
    void setValue(int value);
    String getName();
    void setName(String name);
    String toName();
    Integer toValue();
}