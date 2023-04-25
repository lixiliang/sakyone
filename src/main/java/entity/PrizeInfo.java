package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrizeInfo extends BaseEntity {

    private Integer type;
    private String name;
    private Integer uuid;
    private Long num;

}
