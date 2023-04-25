import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import entity.AppleSubscribe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utill.DateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class TestStr {

    @Test
    public void test() {
        String a = "a";
        String b = a + "b";
        String c = "ab";
        String d = new String(b);
        log.info("a:{},b:{},c:{},d:{}", a, b, c, d);
        System.out.println(b == c);

        System.out.println(d == c);
        System.out.println(c == d.intern());
        System.out.println(c.intern() == d.intern());
        System.out.println(b.intern() == d.intern());
        System.out.println(b == d.intern());
    }

    @Test
    public void test02() {
        List<String> alist = new ArrayList<String>();
        alist.add("c");
        List<String> blist = Arrays.asList("a", "a", "b", "b", "c", "d");
        blist.forEach(b -> {
            if (!alist.contains(b)) {
                alist.add(b);
            }
        });
        System.out.println(alist);
    }

    @Test
    public void test03() {
        //创建集合对象
        List<String> list = new ArrayList<String>();
        //添加元素
        list.add("hello");
        //并发修改异常，创建Iterator迭代器对象
//		Iterator<String> iterator = list.iterator();
//		while (iterator.hasNext()) {
//			String x = iterator.next();
//			if(x.equals("world")) {
//				list.add("javaee");
//			}
//		}
        //方法一
        //for循环方式解决
       /* for(int i=0; i<list.size(); i++) {
            String s = list.get(i);
            if(s.equals("world")) {
                list.add("java");
            }
        }*/
        //方法二
        //通过list集合的listIterator ()方法得到ListIterator列表迭代器对象
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String x = listIterator.next();
            if (x.equals("world")) {
                //注意，这里使用listIterator列表迭代器对象的add方法添加
                listIterator.add("java");
            }
        }
        System.out.println(list);
    }

    @Test
    public void test04() {
        List<AppleSubscribe> list1 = Lists.newArrayList();
        AppleSubscribe a = new AppleSubscribe();
        a.setCurActivityName("a");
        list1.add(a);
        AppleSubscribe b = new AppleSubscribe();
        a.setCurActivityName("b");
        list1.add(b);
        boolean exist = list1.stream().filter(i -> "c".equals(i.getCurActivityName())).findAny().isPresent();
        System.out.println(exist);
    }

    @Test
    public void test054() {
        List<String> table = new ArrayList<>(Arrays.asList("ps_article", "ps_member_collection", "ps_service_num_info", "ps_tag_article", "ps_member_article_like", "ps_article_other", "ps_post_top_pool", "ps_post_info", "ps_post_comment", "ps_vod_info", "ps_topic_info", "ps_vod_saction", "ps_post_like", "ps_post_vote_member", "ps_post_vote_choose", "ps_post_vote_choose_member", "ps_post_at", "ps_post_comment_at", "ps_post_comment_like", "ps_post_info_hyperlink", "ps_article_group", "ps_vod_barrage", "ps_post_barrage", "ps_post_comment_barrage", "ps_barrage_accusation", "ps_comment_sensitive_log", "ps_vod_saction", "ps_vod_barrage_role", "ps_vod_info", "ps_barrage_setting", "ps_privilege_level", "ps_privilege_info", "ps_vod_barrage_like", "ps_post_barrage_like", "ps_post_comment_barrage_like", "ps_drama", "ps_star", "ps_comment_article", "ps_comment_star", "ps_comment_drama", "ps_member_article_comment_like", "ps_member_star_comment_like", "ps_member_drama_comment_like", "ps_service_accusation", "ps_service_member_card", "ps_service_num_info", "ps_service_wanna_see", "ps_service_member_follow", "ps_vod_info", "ps_service_num_activity", "ps_service_activity_entry", "ps_service_content_entry", "ps_vod_like", "ps_service_dynamic_entry", "ps_member_article_like", "ps_service_class", "ps_service_class_entry", "ps_service_notice_entry", "ps_service_notice", "ps_service_member_follow", "ps_service_content_entry", "ps_service_topic_entry", "ps_topic_info", "ps_member_vip", "ps_member_social_data", "mdd_msg.ps_activity_msg_table_mapping", "mdd_msg.ps_activity_msg_*（1-16）", "ps_member_at_msg", "ps_post_info", "ps_post_comment", "ps_vod_info", "ps_member_vip", "ps_post_comment_like", "ps_comment_message", "ps_post_comment_at", "ps_msg_comment", "ps_like_message", "ps_post_comment_at", "ps_mdd_event", "ps_msg_sys", "ps_msg_comment", "ps_comment_message", "ps_like_message", "ps_post_vote_msg", "ps_post_forward_msg", "ps_chat_member", "ps_member_follow", "ps_member_at_msg", "ps_module_info", "ps_module_anchor_entry", "ps_hot_content_recommend", "ps_classify_subject_info", "ps_module_subject_entry", "ps_classify_subject_entry_top", "ps_module_topic_entry", "ps_vod_module_entry", "ps_tab_module_relation", "ps_service_class_module_entry", "ps_service_class_entry", "ps_vod_selected_entry", "ps_module_selected", "ps_vod_info", "ps_module_topic_entry", "ps_topic_follow", "ps_module_post_entry", "ps_classify_subject_info", "ps_module_subject_entry", "ps_hot_content_recommend", "ps_vod_like", "ps_member_article_like", "ps_module_top_banner", "ps_module_user_guide", "ps_module_user_guide_plan", "ps_module_headline", "ps_member_split_table", "ps_vod_watching_history_*", "ps_module_vod_recommend_source", "ps_vod_theme", "ps_vod_theme_source", "ps_module_scroll", "ps_tab_module_relation", "ps_module_single_recommend", "ps_subscribe_event", "ps_module_waterfall", "ps_module_waterfall_tab", "ps_module_waterfall_tab_source", "ps_module_service_transverse", "ps_vod_module_entry", "ps_tab_module_relation", "ps_service_class_module_entry", "ps_service_class", "ps_comment_sensitive_log", "ps_post_barrage", "ps_post_comment_barrage", "ps_barrage_setting", "ps_post_info", "ps_post_comment", "ps_post_comment_accusation", "ps_post_comment_like", "ps_post_comment_at", "ps_vod_info", "ps_member_post_white_list", "ps_keyword", "ps_post_video_source", "ps_member_privilege", "ps_post_audio_resource", "ps_privilege_level", "ps_privilege_info", "ps_post_reply_hot_setting", "ps_member_setting", "ps_push_member_getui", "ps_push_member", "ps_post_accusation", "ps_post_vote_member", "ps_post_vote_choose", "ps_post_vote_choose_member", "ps_post_info_hyperlink", "ps_post_like", "ps_post_coordinate", "ps_vod_extend", "ps_member_post_white_list", "ps_post_vote", "ps_post_vote_choose", "ps_topic_history", "ps_post_video_source", "ps_post_audio_resource", "ps_post_forward_msg", "ps_member_dynamic_content", "ps_member_at_msg", "ps_tab_info", "ps_tab_content_entry", "ps_member_dynamic_content", "ps_vod_role_post", "ps_vod_wonderful_reply", "ps_chat_member_msg", "ps_post_vote_msg", "ps_recommend_white_list", "follow_reveal", "ps_member_follow", "ps_service_member_follow", "ps_service_num_info", "ps_theme_info", "ps_theme_entry", "ps_vod_info", "ps_vod_like", "ps_topic_accusation", "ps_post_top_pool", "ps_post_info_hyperlink", "ps_topic_info", "ps_topic_history", "ps_topic_post_entry"));
        List<String> disTable = table.stream().distinct().collect(Collectors.toList());
        disTable.stream().sorted().forEach(System.out::println);
        System.out.println(disTable.size());
    }

    @Test
    public void test055() {
        String vodHistoryTableHash = "vod:history:table:hash";
        StringBuilder builder = new StringBuilder(vodHistoryTableHash);
        int lenth = vodHistoryTableHash.length();
        for (int i = 0; i < 10; i++) {
            builder.append(":").append(DateUtils.dateToSimpleString(new Date()));
            String key = builder.toString();
            builder.setLength(lenth);
            log.info("key:{}",key);
        }

    }
    @Test
    public void test056() {
        Map<String,String> map = Maps.newHashMapWithExpectedSize(2);
        map.put("a","a1");
        map.put("b","b1");
        map.put("c","c1");
        map.keySet().stream().map(String::toUpperCase);
        map.forEach((k,v)-> log.info("{},{}",k,v));
    }

}
