import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityUnionGoodsVO {

    /**
     * 商户id
     */
    private  Integer unionMchId;

    /**
     * 核销方式
     */
    private Integer verificationType;

    /**
     * 商品id
     */
    private Integer unionGoodsId;

    /**
     * 兑换码
     */
    private String batchCode;

    /**
     * 商品发放形式
     */
    private Integer grantType;

    /**
     * 是否兑换
     */
    private Integer exchange;

}

