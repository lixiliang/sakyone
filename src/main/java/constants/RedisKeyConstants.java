package constants;

/**
 * 用于所用到redis的key
 * 命名规范：工程代号:业务模块:具体功能 (每部分控制在5个字符内)
 * 请尽量精简key，提倡简写,缩写,减少redis key的内存占用,但变量名请尽量保持清晰
 */
public interface RedisKeyConstants {
    String LIVE_GIFT_ALL = "live:giftLs:all";
    String LIVE_HANGINGS_ALL = "live:hang:all";
    int LIVE_GIFT_ALL_EXTIME = 900;
    String LIVE_MANAGER_LIST = "live:manLs:";
    String LIVE_FORBID_LIST = "live:forbLs:";
    String LIVE_KICK_LIST = "live:kickLs:";
    String LIVE_BLOCK_LIST = "live:blockLs:";
    String LIVE_SOUND_EFFECT_LIST = "live:soundLs:";
    //本场实时在线星光榜 topN key前缀
    String LIVE_ROUND_ONLINE_STAR_TOPN_PRE = "live:ro:star:top:";
    //本场星光榜(所有用户)key
    String LIVE_ROUND_ALL_STAR = "live:ra:star:all";
    //粉丝团
    String LIVE_FAN_CLUB = "live:fan:club:";
}
