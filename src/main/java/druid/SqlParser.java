package druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiliang on 2018/6/8.
 */
@Slf4j
public class SqlParser {

    public static List<String> parseTable(String sql){
        List<String> result = Lists.newArrayList();
        try {
            String dbType = JdbcConstants.MYSQL;
            List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
            SQLStatement stmt = stmtList.get(0);

            SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(dbType);
            stmt.accept(statVisitor);
            Map<TableStat.Name, TableStat> tmaps =  statVisitor.getTables();
            Iterator iter = tmaps.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                TableStat.Name key =(TableStat.Name) entry.getKey();
                result.add(StringUtils.lowerCase(key.getName()));
            }
        } catch (Exception e) {
            log.error("error sql:{},e",sql,e);
        }
        return result;
    }

    public static void main(String[] args) {
//        String sql = "SELECT count(0) from( SELECT 1 FROM customer_Member cm INNER JOIN customer c on cm.id=c.member_id INNER JOIN customer_account ca ON c.id = ca.customer_id WHERE cm.shop_id= ? AND cm.member_group_id in ( ? ) )aa;";
        String sql = "SELECT uuid, post_uuid, content, image_array, cover_img, video_url, audio_url, audio_time, status, num, member_uuid, member_name, member_image, approve_time, create_time, comment_num, seq, like_num, def_like_num, comment_type, comment_uuid, comment_member_uuid, comment_member_name, comment_member_image, content_type, source_uuid, is_trans, data_type, article_uuid, article_title, vod_uuid, vod_name, service_uuid, service_name, topic_uuid, topic_name, head_image, nick_name, duration, size, is_top, is_selected, width, height, is_sync_image, top_seq, point_num, is_at, first_level_uuid, second_level_uuid, level, sub_num, last_modify_time, is_del, del_user,image_resolution_ratio,down_image,face_url,face_uuid FROM (SELECT uuid, post_uuid, content, image_array, cover_img, video_url, audio_url, audio_time, status, num, member_uuid, member_name, member_image, approve_time, create_time, comment_num, seq, like_num, def_like_num, comment_type, comment_uuid, comment_member_uuid, comment_member_name, comment_member_image, content_type, source_uuid, is_trans, data_type, article_uuid, article_title, vod_uuid, vod_name, service_uuid, service_name, topic_uuid, topic_name, head_image, nick_name, duration, size, is_top, is_selected, width, height, is_sync_image, top_seq, point_num, is_at, first_level_uuid, second_level_uuid, level, sub_num, last_modify_time, is_del, del_user,image_resolution_ratio,down_image,face_url,face_uuid , (@num:=case when @comment_uuid=comment_uuid then @num + 1 else 1 end )AS row_number, (@comment_uuid:=comment_uuid) AS dummy FROM mdd.ps_post_comment ,(select @num:=0,@comment_uuid:='') as q WHERE is_del = 0 and comment_type=1 and is_trans = 1 and is_del = 0 and status=1 and point_num>=50 and comment_uuid in ('2f3a821060084570b251d6be225b3b89','26f52a827bbe4efb91115b90e7f67a61','9fa2acfc6a1540eb9c143beeaf94fd5d') ORDER BY comment_uuid desc, point_num DESC , create_time ASC) AS T WHERE row_number = 1";
        List<String> tables = SqlParser.parseTable(sql);
        System.out.println(tables);
        String psql = ParameterizedOutputVisitorUtils.parameterize(sql, JdbcConstants.MYSQL).toLowerCase();
        System.out.println(psql);
    }
}
