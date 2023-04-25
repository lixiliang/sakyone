package entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description 剧集信息
 * @author sakyone
 * @date 2023-02-06
 */
@Accessors(chain = true)
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VodInfoPo extends BaseEntity {


    /**
    * uuid
    */
    private String uuid;

    /**
    * 点播剧集名称
    */
    private String name;

    /**
    * 视频名称(英文)
    */
    private String nameEn;

    /**
    * 真实播放量
    */
    private Integer playNum;

    /**
    * 默认基础播放量
    */
    private Integer defPlayNum;

    /**
    * 更新集数
    */
    private Integer updateNum;

    /**
    * 总集数
    */
    private Integer totalNum;

    /**
    * 点播类型 0 电视剧集 、1 电影、2 综艺节目、3 小视频、4 直播回放视频、5 个人号直播回复视频
    */
    private Integer vodType;

    /**
    * 视频分类标签，英文逗号分隔
    */
    private String vodTag;

    /**
    * 试看开关 0开 、1 关
    */
    private Integer tryFlag;

    /**
    * 试看时长
    */
    private Integer tryTime;

    /**
    * 导演
    */
    private String director;

    /**
    * 监制
    */
    private String monitor;

    /**
    * 编剧
    */
    private String screenplay;

    /**
    * 主演
    */
    private String starring;

    /**
    * 分类状态 0 暂存、 1 上线、2 下线
    */
    private Integer status;

    /**
    * 评分
    */
    private Double grade;

    /**
    * 剧集简介
    */
    private String introduction;

    /**
    * 点播封面图
    */
    private String coverImage;

    /**
    * 上映时间
    */
    private Date releaseTime;

    /**
    * 微博分享开关 0 可以分享 、1 不能分享
    */
    private Integer wbShareFlag;

    /**
    * 微信分享开关 0 可以分享 、1 不能分享
    */
    private Integer wxShareFlag;

    /**
    * 微信朋友圈分享开关 0 可以分享 、1 不能分享
    */
    private Integer wxcirShareFlag;

    /**
    * qq分享开关 0 可以分享 、1 不能分享
    */
    private Integer qqShareFlag;

    /**
    * qq空间分享开关 0 可以分享 、1 不能分享
    */
    private Integer qqcirShareFlag;

    /**
    * 关联剧集资料id
    */
    private String relaDramaUuid;

    /**
    * 是否可以分享 0 可以 、1 不可以
    */
    private Integer isShare;

    /**
    * share_title
    */
    private String shareTitle;

    /**
    * 分享副标题
    */
    private String shareSubTitle;

    /**
    * 分享图片
    */
    private String shareImage;

    /**
    * 分享链接
    */
    private String shareUrl;

    /**
    * 收藏总数
    */
    private Integer colectionNum;

    /**
    * 评论总数
    */
    private Integer commentNum;

    /**
    * 首播时间
    */
    private Date firstSowingTime;

    /**
    * 编审
    */
    private String copyEditor;

    /**
    * 主要嘉宾
    */
    private String mainGuest;

    /**
    * 节目简介
    */
    private String varietyIntroduction;

    /**
    * 横图
    */
    private String sideImage;

    /**
    * 竖图
    */
    private String downImage;

    /**
    * 是否开启相关剧集 0 开启 1 关闭
    */
    private Integer relaVodFlag;

    /**
    * rela_vod_num
    */
    private Integer relaVodNum;

    /**
    * 是否开启相关文章 0 开启 1 关闭
    */
    private Integer relaArticleFlag;

    /**
    * rela_article_num
    */
    private Integer relaArticleNum;

    /**
    * 剧集描述(副标题用free_desc)
    */
    private String subTitle;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建人id
    */
    private String createUserId;

    /**
    * 创建人名称
    */
    private String createUserName;

    /**
    * 最后修改时间
    */
    private Date lastModifyTime;

    /**
    * 最后修改人id
    */
    private String lastModifyUserId;

    /**
    * 最后修改人姓名
    */
    private String lastModifyUserName;

    /**
    * 序号
    */
    private Integer seq;

    /**
    * 数组形式
    */
    private String tagUuidArray;

    /**
    * 隐藏选集栏开关 0开、1关(不隐藏)
    */
    private Integer hideSactionFlag;

    /**
    * service_uuid
    */
    private String serviceUuid;

    /**
    * 点赞数
    */
    private Integer likeNum;

    /**
    * share_num
    */
    private Integer shareNum;

    /**
    * def_like_num
    */
    private Integer defLikeNum;

    /**
    * def_share_num
    */
    private Integer defShareNum;

    /**
    * bbc视频资源唯一uuid
    */
    private String bbcVodUuid;

    /**
    * mp视频uuid
    */
    private String mpVodUuid;

    /**
    * id
    */
    private Integer id;

    /**
    * app内查询时隐藏开关：0不隐藏、1隐藏
    */
    private String isAppHide;

    /**
    * 直播uuid
    */
    private String liveUuid;

    /**
    * 直播期间观看人数
    */
    private Integer liveNum;

    /**
    * 0 竖屏 、1 横屏
    */
    private Integer liveVideoType;

    /**
    * 试看地址
    */
    private String tryWatchUrl;

    /**
    * 直播回放视频地址
    */
    private String liveVideoUrl;

    /**
    * 直播星光值
    */
    private Integer liveStarValue;

    /**
    * 直播主播用户uuid
    */
    private String liveMemberUuid;

    /**
    * 第三方视频播放封面图
    */
    private String playCoverImage;

    /**
    * 第三方播放视频链接
    */
    private String thirdPlayUrl;

    /**
    * 是否显示到视频列表 0 否、1 是
    */
    private Integer showVideo;

    /**
    * 视频列表排序号
    */
    private Integer videoSeq;

    /**
    * 是否热门 0 否、1是
    */
    private Integer isHot;

    /**
    * 是否精选 0 否、1是
    */
    private Integer isSelected;

    /**
    * 分类主题名称
    */
    private String vodSubject;

    /**
    * 分类主题uuid
    */
    private String vodSubjectUuidArray;

    /**
    * 是否第三方链接播放 0 否、1 是
    */
    private Integer isThird;

    /**
    * 是否置顶 0 否、1 是
    */
    private Integer isTop;

    /**
    * 目前使用的cdn类型，默认0腾讯，1网宿，2云帆，3七牛，4华为， 5ucloud，6jd
    */
    private Integer cdnType;

    /**
    * cdn分流流量(0-100)
    */
    private Integer cdnTraffic;

    /**
    * 是否限时剧 0否、1是(限时剧不可以收藏)
    */
    private Integer isTimeLimit;

    /**
    * 是否vip剧集 0 否、1 是
    */
    private Integer isVip;

    /**
    * 年份
    */
    private String yearName;

    /**
    * year_array
    */
    private String yearArray;

    /**
    * 题材
    */
    private String materialName;

    /**
    * material_array
    */
    private String materialArray;

    /**
    * 地区
    */
    private String regionName;

    /**
    * region_array
    */
    private String regionArray;

    /**
    * 开启弹幕角色 0 否、1 是
    */
    private Integer openBarrageRole;

    /**
    * 平台 0腾讯 1网宿
    */
    private Integer platform;

    /**
    * 片头时长
    */
    private Integer startTimes;

    /**
    * 片尾时长
    */
    private Integer endTimes;

    /**
    * 多码率是否打开：0 否、1 是
    */
    private Integer multiRateOpen;

    /**
    * 支持的码率
    */
    private String rateSetting;

    /**
    * vip专有码率
    */
    private String vipRateSetting;

    /**
    * 支持的码率数组
    */
    private String rateSettingArray;

    /**
    * vip专有的码率数组
    */
    private String vipRateSettingArray;

    /**
    * 视频所属用户
    */
    private String memberUuid;

    /**
    * 缓存权限 0：不能缓存 1：所有用户均可缓存 2：vip用户可缓存
    */
    private Integer cacheType;

    /**
    * 角标图片
    */
    private String cornerImage;

    /**
    * 限免说明/副标题
    */
    private String freeDesc;

    /**
    * 上线预约时间
    */
    private Date collectionTime;

    /**
    * 未登录支持的码率
    */
    private String notLoginRateSetting;

    /**
    * 所有支持的码率
    */
    private String totalRateSetting;
}