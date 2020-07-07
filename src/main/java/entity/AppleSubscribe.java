package entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
*  ps_apple_subscribe
* @author sakyone 2019-10-14
*/
@Data
public class AppleSubscribe extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
    * 订阅id，主键
    */
    private Integer uuid;

    private String orderUuid;

    /**
    * 订阅组，0 vip组
    */
    private Integer subscribeGroup;

    /**
    * 用户uuid
    */
    private String memberUuid;

    /**
    * 用户昵称
    */
    private String nickName;

    /**
    * 用户手机号码
    */
    private String phoneNum;

    /**
    * 当前订阅开始时间
    */
    private Date beginTime;

    /**
    * 当前订阅结束时间
    */
    private Date endTime;

    /**
    * 当前订阅商品id
    */
    private String curGoodsUuid;

    /**
    * 当前订阅商品名称
    */
    private String curGoodsName;

    /**
    * 当前订阅商品价格，有活动则取活动价，无活动取商品原价
    */
    private BigDecimal curGoodsPrice;

    /**
    * 当前活动uuid
    */
    private String curActivityUuid;

    /**
    * 当前订阅商品活动名称
    */
    private String curActivityName;

    /**
    * 下次订阅商品名称
    */
    private String nextGoodsUuid;

    /**
    * 下次订阅商品名称
    */
    private String nextGoodsName;

    /**
    * 下次订阅商品名称
    */
    private String nextActivityUuid;

    /**
    * 下次订阅商品名称
    */
    private String nextActivityName;

    /**
    * 下次订阅商品价格
    */
    private BigDecimal nextGoodsPrice;

    /**
    * 订阅状态 0正常订阅 1已退款 2已过期 3关闭自动订阅
    */
    private Integer status;

    /**
    * 连续订阅次数
    */
    private Integer continueTimes;

    /**
    * 变更原因
    */
    private String reason;

    /**
    * create_time
    */
    private Date createTime;

    /**
    * last_modify_time
    */
    private Date lastModifyTime;

}