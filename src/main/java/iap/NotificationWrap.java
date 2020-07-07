package iap;

import entity.BaseEntity;
import lombok.*;

/**
*  苹果服务器通知事件记录
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class NotificationWrap extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Notification notification;
    private AppleNotification appleNotification;

}