package iap;

import entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
*  苹果服务器通知事件记录
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class AppleNotification extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
    * uuid
    */
    private Integer uuid;

    /**
    * 通知内容数据
    */
    private String httpBody;

    /**
    * 通知类型 initial_buy、 cancel 、renewal、interactive_renewal、did_change_renewal_pref、did_change_renewal_status
    */
    private String notificationType;

    /**
    */
    private String transactionId;
    private String originalTransactionId;
    private String iapId;

    /**
    * 服务端处理状态 0未处理 1处理中 2处理成功 3处理失败
    */
    private Integer status;

    /**
    * 处理结果说明
    */
    private String message;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * update_time
    */
    private Date updateTime;

}