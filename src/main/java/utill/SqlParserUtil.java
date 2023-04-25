package utill;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.List;

/**
 * Created by lixiliang on 2018/11/16.
 */
public class SqlParserUtil {
    public static List<String> getTableNameBySql(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);
        return tableList;
    }

    public static String getOneTableNameBySql(String sql) throws JSQLParserException {
        String result = "";
        List<String> tableList = getTableNameBySql(sql);
        if(tableList != null && !tableList.isEmpty()){
            result = tableList.get(0);
        }
        return result;
    }

    public static void main(String[] args) {
//        String sql = "SELECT po.id, po.title, po.total_fee, po.status, po.type, po.source, po.payment_method, po.sub_status, po.third_order_id, po.biz_data, po.remark, po.deliver_fee, po.undiscountable_fee, po.discount, po.discount_fee, po.bonus_discount, po.bonus_discount_num, po.bonus_discount_fee, po.not_remainder_fee, po.shop_id, po.shop_name, po.shop_serial, po.shop_phone, po.shop_branch_id, po.shop_branch_name, po.shop_branch_phone, po.shop_branch_serial, po.shop_branch_account, po.device_id, po.device_no, po.device_name, po.operator_id, po.operator_number, po.operator_name, po.customer_id, po.customer_name, po.customer_phone, po.customer_serial, po.member_name, po.member_phone, po.create_time, po.update_time, po.version, po.pay_deadline, po.locked, po.parent_order_id, po.valid_member_id, po.order_fee FROM product_order po WHERE po.parent_order_id = '201044906202011807744' AND po.status = 4 ORDER BY po.create_time DESC";
        String sql = "SELECT t1.is_hot, t1.is_top ,t2.nick_name,t2.headimgurl FROM ps_vod_comment t1  " +
                "                left join  ps_member t2 on t1.member_uuid = t2.uuid  " +
                "                where t1.vod_uuid = '' and t1.is_del=0 and comment_type = 1" +
                "                order by t1.is_top desc,t1.sorting asc,t1.create_time desc  ";
//        String sql = "update T set a='xxx'";
        try {
            List<String> tableList = getTableNameBySql(sql);
            System.out.println(tableList);
            String tn = getOneTableNameBySql(sql);
            System.out.println(tn);
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
    }
}
