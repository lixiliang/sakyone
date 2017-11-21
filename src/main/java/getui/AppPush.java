package getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.*;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.NotyPopLoadTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AppPush {

    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "7Pxd0EFVf19UlgjJADZeO4";
    private static String appKey = "WS0mG4VtFx7Y68NMJfpgX6";
    private static String masterSecret = "GyRz7fzQab6HieodHJyiu8";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    private static IGtPush push = new IGtPush(url, appKey, masterSecret);

    public static void main(String[] args) throws IOException {
        String cid_hm = "c3cc590ea264518848044f86c0a94ce8";
        String cid_max="9d5d49f5a13609762014c7f25fa0d9ac";
        List<String> cids=new ArrayList<String>();
        cids.add(cid_hm);
//        cids.add(cid_max);

        String taskId_single="OSS-0927_ccb47db44854d993be5349777cd7c2f1";
        String taskId_list="OSL-0927_fLMdB7qFpY5fIGsZ6dWio8";
        queryMsgState(taskId_single);
        queryMsgState(taskId_list);
//        pushToSingle(cid_hm);
//        pushToApp(appId);
//        pushToList(cids);
    }

    /**
     * 在通知栏显示一条含图标、标题等的通知，用户点击可打开您指定的网页。
     * @param title
     * @return
     */
    private static LinkTemplate linkTemplateDemo(String title) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("通知栏标题:"+title);
        style.setText("通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl("http://www.baidu.com");
        return template;
    }

    /**
     * 在通知栏显示一条含图标、标题等的通知，用户点击后激活您的应用
     * 激活后，打开应用的首页，如果只要求点击通知唤起应用，不要求到哪个指定页面就可以用此功能。
     * @return
     */
    public static NotificationTemplate notificationTemplateDemo() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("通知栏标题");
        style.setText("通知栏内容:透传");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("请输入您要透传的内容");
        return template;
    }

    /**
     * 消息以弹框的形式展现，点击弹框内容可启动下载任务。
     * @param appId
     * @param appKey
     * @return
     */
    public static NotyPopLoadTemplate notyPopLoadTemplateDemo(String appId, String appKey) {
        NotyPopLoadTemplate template = new NotyPopLoadTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置弹框标题与内容
        template.setPopTitle("弹框标题");
        template.setPopContent("弹框内容");
        // 设置弹框显示的图片
        template.setPopImage("");
        template.setPopButton1("下载");
        template.setPopButton2("取消");
        // 设置下载标题
        template.setLoadTitle("下载标题");
        template.setLoadIcon("file://icon.png");
        //设置下载地址
        template.setLoadUrl("http://gdown.baidu.com/data/wisegame/80bab73f82cc29bf/shoujibaidu_16788496.apk");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    /**
     * 透传消息是指消息传递到客户端只有消息内容，展现形式由客户端自行定义。
     * @return
     */
    public static TransmissionTemplate transmissionTemplateDemo(String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("透传的内容:"+content);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    public static void pushToApp(String appId){
        LinkTemplate template = linkTemplateDemo("by app");
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage appMessage = new AppMessage();
        appMessage.setData(template);
        appMessage.setAppIdList(appIds);
        appMessage.setOffline(true);
        appMessage.setOfflineExpireTime(1000 * 600);
        IPushResult ret = push.pushMessageToApp(appMessage);
        System.out.println(ret.getResponse().toString());
    }
    public static void pushToSingle(String clientId){
//        LinkTemplate template = linkTemplateDemo("by single");
        TransmissionTemplate template = transmissionTemplateDemo(" my content");
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0);
        Target target = new Target();
        target.setAppId(appId);
        target.setClientId(clientId);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
    public static void pushToList(List<String> clientIds){
        System.setProperty("gexin_pushList_needDetails", "true");
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo();
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        for (String cid:clientIds) {
            Target t = new Target();
            t.setAppId(appId);
            t.setClientId(cid);
            targets.add(t);
        }
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
    }
    public static void queryMsgState(String taskId){
        IPushResult result = push.getPushResult(taskId);
        System.out.println(result.getResponse().toString());
    }
}

