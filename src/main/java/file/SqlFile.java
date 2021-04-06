package file;

import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import druid.SqlParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author lixiliang
 * @describe
 * @date 2021/3/26
 */
@Slf4j
public class SqlFile {
    public static List<String> TARGET_TABLES = Arrays.asList("ps_post_info"
            ,"ps_post_comment"
            ,"ps_member"
            ,"content"
            ,"ps_article"
            ,"ps_member_mount"
            ,"ps_vod_info"
            ,"ps_chat_member"
            ,"ps_member_source"
            ,"ps_push"
            ,"ps_like_message"
            ,"ps_push_record"
            ,"ps_article_my"
            ,"ps_comment_message"
            ,"ps_member_dynamic_content"
            ,"ps_chat_member_msg"
            ,"ps_member_info_approve"
            ,"ps_recommend_top_pool"
            ,"ps_article_spider"
            ,"ps_search_face_info"
            ,"ps_hot_content_recommend"
            ,"ps_post_forward_msg"
            ,"ps_push_member"
            ,"ps_vod_recognition_task"
            ,"s_member_reward_task_log"
            ,"ps_post_video_source"
            ,"ps_service_num_info"
            ,"ps_code_msg_mini_program"
            ,"ps_post_coordinate"
            ,"ps_chat_member_block"
            ,"ps_member_mini_program"
            ,"s_member_reward_action_log"
            ,"ps_live_gift_history");

    public static void main(String[] args) throws IOException {
        String dir = "e:/tmp/";
        String fileName = "29.sql";
        File file = new File(dir+fileName);
//        List<String> lines = FileUtils.readLines(file,"UTF-8");
        List<String> sqls = Lists.newArrayList();
        Map<String,List<String>> sqlMaps = Maps.newHashMap();
        LineIterator it = null;
        try {
            it = FileUtils.lineIterator(file, "UTF-8");
            while (it.hasNext()){
                String line = it.nextLine();
                int first = line.indexOf("\"");
                if(first < 0){
                    continue;
                }
                String sql = line.substring(first+1,line.lastIndexOf("\""));
    //            log.info(sql);
                List<String> tables = SqlParser.parseTable(sql);
                boolean contains = tables.stream().anyMatch(t-> TARGET_TABLES.contains(t));
                String tkeys = StringUtils.join(tables,",");
                if(contains){
                    //参数化
                    String psql = ParameterizedOutputVisitorUtils.parameterize(sql, JdbcConstants.MYSQL).toLowerCase();
                    if(!sqls.contains(psql)){
                        sqls.add(psql);

                        if(sqlMaps.containsKey(tkeys)){
                            sqlMaps.get(tkeys).add(psql);
                        }else {
                            List<String> ele = Lists.newArrayList();
                            ele.add(psql);
                            sqlMaps.put(tkeys,ele);
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (it != null) {
                it.close();
            }
        }
       /* lines.stream().forEach(line ->{

        });*/

        sqlMaps.forEach((k,v)->{
            System.out.println("==================Table:"+k+"==================");
            String content = StringUtils.join(v,"\n");
//            String name = fileName.substring(0,fileName.indexOf("."));
            String filePath = dir+"/"+k+"/" +fileName;
            writeText(filePath,content);
        });
//        sqls.forEach(sql->log.info("sql:{}",sql));
//        sqls.forEach(System.out::println);
    }

    public static void writeText(String filePath, String content) {
        try {
            FileUtils.write(new File(filePath),content,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
