package druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
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
        String sql = "SELECT\n" +
                "  uuid,\n" +
                "  post_uuid,\n" +
                "  content,\n" +
                "  image_array,\n" +
                "  cover_img,\n" +
                "  video_url,\n" +
                "  audio_url,\n" +
                "  audio_time,\n" +
                "  status,\n" +
                "  num,\n" +
                "  member_uuid,\n" +
                "  member_name,\n" +
                "  member_image,\n" +
                "  approve_time,\n" +
                "  create_time,\n" +
                "  comment_num,\n" +
                "  seq,\n" +
                "  like_num,\n" +
                "  def_like_num,\n" +
                "  comment_type,\n" +
                "  comment_uuid,\n" +
                "  comment_member_uuid,\n" +
                "  comment_member_name,\n" +
                "  comment_member_image,\n" +
                "  content_type,\n" +
                "  source_uuid,\n" +
                "  is_trans,\n" +
                "  data_type,\n" +
                "  article_uuid,\n" +
                "  article_title,\n" +
                "  vod_uuid,\n" +
                "  vod_name,\n" +
                "  service_uuid,\n" +
                "  service_name,\n" +
                "  topic_uuid,\n" +
                "  topic_name,\n" +
                "  head_image,\n" +
                "  nick_name,\n" +
                "  duration,\n" +
                "  size,\n" +
                "  is_top,\n" +
                "  is_selected,\n" +
                "  width,\n" +
                "  height,\n" +
                "  is_sync_image,\n" +
                "  top_seq,\n" +
                "  point_num,\n" +
                "  is_at,\n" +
                "  first_level_uuid,\n" +
                "  second_level_uuid,\n" +
                "  level,\n" +
                "  sub_num,\n" +
                "  last_modify_time,\n" +
                "  is_del,\n" +
                "  del_user,\n" +
                "  image_resolution_ratio,\n" +
                "  down_image,\n" +
                "  face_url,\n" +
                "  face_uuid\n" +
                "FROM\n" +
                "  (\n" +
                "    SELECT\n" +
                "      uuid,\n" +
                "      post_uuid,\n" +
                "      content,\n" +
                "      image_array,\n" +
                "      cover_img,\n" +
                "      video_url,\n" +
                "      audio_url,\n" +
                "      audio_time,\n" +
                "      status,\n" +
                "      num,\n" +
                "      member_uuid,\n" +
                "      member_name,\n" +
                "      member_image,\n" +
                "      approve_time,\n" +
                "      create_time,\n" +
                "      comment_num,\n" +
                "      seq,\n" +
                "      like_num,\n" +
                "      def_like_num,\n" +
                "      comment_type,\n" +
                "      comment_uuid,\n" +
                "      comment_member_uuid,\n" +
                "      comment_member_name,\n" +
                "      comment_member_image,\n" +
                "      content_type,\n" +
                "      source_uuid,\n" +
                "      is_trans,\n" +
                "      data_type,\n" +
                "      article_uuid,\n" +
                "      article_title,\n" +
                "      vod_uuid,\n" +
                "      vod_name,\n" +
                "      service_uuid,\n" +
                "      service_name,\n" +
                "      topic_uuid,\n" +
                "      topic_name,\n" +
                "      head_image,\n" +
                "      nick_name,\n" +
                "      duration,\n" +
                "      size,\n" +
                "      is_top,\n" +
                "      is_selected,\n" +
                "      width,\n" +
                "      height,\n" +
                "      is_sync_image,\n" +
                "      top_seq,\n" +
                "      point_num,\n" +
                "      is_at,\n" +
                "      first_level_uuid,\n" +
                "      second_level_uuid,\n" +
                "      level,\n" +
                "      sub_num,\n" +
                "      last_modify_time,\n" +
                "      is_del,\n" +
                "      del_user,\n" +
                "      image_resolution_ratio,\n" +
                "      down_image,\n" +
                "      face_url,\n" +
                "      face_uuid,\n" +
                "      (\n" +
                "        case\n" +
                "          when post_uuid = @post_uuid then @num: = @num + 1\n" +
                "          else @num: = 1\n" +
                "        end\n" +
                "      ) AS row_number,\n" +
                "      (@post_uuid: = post_uuid)\n" +
                "    FROM\n" +
                "      mdd.ps_post_comment force index(ind_post_pointNum_createTime),(\n" +
                "        select\n" +
                "          @num: = 0,\n" +
                "          @post_uuid: = ''\n" +
                "      ) as q\n" +
                "    WHERE\n" +
                "      is_del = 0\n" +
                "      and comment_type = 0\n" +
                "      and is_trans = 1\n" +
                "      and status = 1\n" +
                "      and point_num >= 50\n" +
                "      and post_uuid in (\n" +
                "        'd8c6ab8613984599b798abb53303124d',\n" +
                "        '75f95bc9a8004b77a088100905602727',\n" +
                "        '37e7bb40299c4949a66b75b96d0f9e5f'\n" +
                "      )\n" +
                "    ORDER BY\n" +
                "      post_uuid desc,\n" +
                "      point_num DESC,\n" +
                "      create_time ASC\n" +
                "  ) AS T\n" +
                "WHERE\n" +
                "  row_number = 1";
        List<String> tables = SqlParser.parseTable(sql);
        System.out.println(tables);
    }
}
