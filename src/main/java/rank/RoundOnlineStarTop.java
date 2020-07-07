package rank;

import constants.RedisKeyConstants;
import entity.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
/**
 * 实时在线用户-星光榜(场榜)
 * 先按星光值倒序排,再按时间顺序排
 */
public class RoundOnlineStarTop extends BaseEntity implements Rank {
    //榜单场次
    private Integer liveUuid;
    //排序主体id
    private String memberUuid;
    //星光值
    private int starValue;
    //数据时间
    private Date dataTime;

    @Override
    public String buildRankName() {
        return RedisKeyConstants.LIVE_ROUND_ONLINE_STAR_TOPN_PRE + liveUuid;
    }

    @Override
    public String buildDataId() {
        return this.memberUuid;
    }

    @Override
    public double buildScore() {
        return starValue + (MAX_VALUE - dataTime.getTime());
    }

    @Override
    public String buildLock() {
        return (buildRankName() + buildDataId()).intern();
    }

}
