package enums;

import java.util.Map;

public interface MddEnum {
    public Map<Integer, String> toMap();
    public Integer getValue();
    public void setValue(int value);
    public String getName();
    public void setName(String name);
    public String toName();
    public Integer toValue();
}