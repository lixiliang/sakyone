package rank;

/**
 * https://segmentfault.com/a/1190000011737336
 *
 * 活动周期在92天以内
 * score在1亿以内
 * 精度秒
 */
public interface Rank {
    long BASE_NUM = 2000000;
    int DURATION_SECOND = 60 * 60 * 24 * 90;
    long MAX_VALUE = BASE_NUM + DURATION_SECOND;

    //榜单名称对应redis key
    String buildRankName();
    //排序主体id
    String buildDataId();
    //分数
    double buildScore();

   String buildLock();
}
