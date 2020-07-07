package json;

import entity.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MemberBaseInfo extends BaseEntity {

    private String memberUuid;
    private String nickName;
    private String headimgurl;
//    @JSONField(serialize = false)
    private String num;
    public String getNum() {
        return num;
    }
}