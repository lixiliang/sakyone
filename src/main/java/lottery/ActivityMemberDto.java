package lottery;

import entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityMemberDto extends BaseEntity {
    private String memberUuid;
    private String source;
    private Long createTimestamp = System.currentTimeMillis();
}
