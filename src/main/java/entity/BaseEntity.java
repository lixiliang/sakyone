package entity;

import com.alibaba.fastjson.JSON;

/**
 * 实体对象基类
 */
public class BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return JSON.toJSONStringWithDateFormat(this,"yyyy-MM-dd HH:mm:ss");
    }

}