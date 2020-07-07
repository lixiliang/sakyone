package entity;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RankConfig extends BaseEntity {
    private String rank;
    private String name;
    private String type;
    private int isHide;
}
