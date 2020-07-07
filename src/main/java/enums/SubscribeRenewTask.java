package enums;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
*  会员自动续费任务
* @author sakyone 2019-10-28
*/
@Data
public class SubscribeRenewTask implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
    * 任务id，主键
    */
    private Integer uuid;

    /**
    * 订阅记录uuid
    */
    private Integer subscribeUuid;

    /**
    * 用户uuid
    */
    private String memberUuid;

    /**
    * 订阅开始时间
    */
    private Date subscribeBeginTime;

    /**
    * 订阅结束时间
    */
    private Date subscribeEndTime;

    /**
    * 任务开始处理时间
    */
    private Date taskBeginTime;

    /**
    * 任务处理结束时间
    */
    private Date taskEndTime;

    /**
    * 生成的订单uuid
    */
    private String orderUuid;

    /**
    * 处理状态 0未处理 1处理中 2处理成功 3处理失败 4已取消
    */
    private Boolean status;

    /**
    * 信息说明
    */
    private String message;

    public SubscribeRenewTask() {
    }

}