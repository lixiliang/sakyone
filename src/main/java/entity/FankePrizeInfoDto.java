package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FankePrizeInfoDto extends BaseEntity {

    private Integer id;
    private String name;
    private Long num;


    public static FankePrizeInfoDto transfer(PrizeInfo prizeInfo) {
        if (prizeInfo == null) {
            return null;
        }
        return new FankePrizeInfoDto().setId(prizeInfo.getUuid()).setName(prizeInfo.getName()).setNum(prizeInfo.getNum());
    }
}
