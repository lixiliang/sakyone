package enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * API 提示信息
 */
public enum TipsEnum implements MddEnum {
    SUCCESS("操作成功", 0),
    SYSTEM_BUSY("堆仔很忙，请稍候", -1),
    UNLOGIN("请登录账号，让我重新认识你", 1),

    PARAM_IS_ILLEGAL("非法参数", 1000),
    DATA_IS_NEED("堆仔收不到哦", 1010),
    PARAM_IS_EMPTY("参数[%s]不能为空", 1010);

    private String name;
    private Integer value;

    TipsEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(int value) {
        for (TipsEnum c : TipsEnum.values()) {
            if (c.getValue() == value) {
                return c.getName();
            }
        }
        return null;
    }

    public static TipsEnum getEnum(int value) {
        for (TipsEnum c : TipsEnum.values()) {
            if (c.getValue() == value) {
                return c;
            }
        }
        return null;
    }

    public static TipsEnum getEnum(String name) {
        for (TipsEnum c : TipsEnum.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Map<Integer, String> toMap() {
        LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>();
        for (TipsEnum c : TipsEnum.values()) {
            map.put(c.getValue(), c.getName());
        }
        return map;
    }

    public String toName() {
        return this.name == null ? this.name() : this.name;
    }

    public Integer toValue() {
        return this.value == null ? this.ordinal() : this.value;
    }

}
