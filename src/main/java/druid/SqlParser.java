package druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiliang on 2018/6/8.
 */
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
                result.add(key.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String sql = "SELECT count(0) from( SELECT 1 FROM customer_member cm INNER JOIN customer c on cm.id=c.member_id INNER JOIN customer_account ca ON c.id = ca.customer_id WHERE cm.shop_id= ? AND cm.member_group_id in ( ? ) )aa;";
        List<String> tables = SqlParser.parseTable(sql);
        System.out.println(tables);
    }
}
