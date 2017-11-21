package jdbc;

import java.sql.*;

public class Mycat {

    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql://10.10.50.127:8066/saofu?user=dev&password=yunnex&useUnicode=true&characterEncoding=UTF8";
//    public static final String url = "jdbc:mysql://192.168.10.236:8086/testdb?user=mycat&password=123456&useUnicode=true&characterEncoding=UTF8";

    public Connection conn = null;
    public PreparedStatement pst = null;

    private PreparedStatement getPst(String sql) {
        try {  
            Class.forName(driver);//指定连接类型
            conn = DriverManager.getConnection(url);//获取连接
//            conn.setAutoCommit(false);
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return pst;
    }  
    public void close() {
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }
    public void select(){
        try {
//            String sql = "select * from user";
            /*!mycat:db_type=master*/
            String sql = "select * from account_operator where id=1";
//            String sql = "select id,parent, serial, phone_number, email, name, logo_url, print_ad_url, print_ad_text, create_time, full_name,suffix_shop,third_app_id,shop_type from shop where serial = '10522647986413632376'";
            ResultSet ret = getPst(sql).executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                String uid = ret.getString("name");
                /*String ufname = ret.getString(2);
                String ulname = ret.getString(3);
                String udate = ret.getString(4);*/
                System.out.println(uid);
            }//显示数据
/*            PreparedStatement ps = jdbc.conn.prepareStatement("commit");
            ps.executeQuery();*/
//            conn.commit();
            ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Mycat().select();
    }
}  