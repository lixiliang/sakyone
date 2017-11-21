package jdbc;

import java.sql.*;

public class JDBC {
    public static final String port = "3306";
    public static final String db = "db1";
    public static final String host = "192.168.10.235";

    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "root";
    public static final String url = "jdbc:mysql://"+host+":"+port+"/"+db
            +"?user="+user
            +"&password="+password
            +"&useUnicode=true&characterEncoding=UTF8";

    public Connection conn = null;  
    public PreparedStatement pst = null;  
  
    public JDBC(String sql) {
        try {  
            Class.forName(driver);//指定连接类型
            conn = DriverManager.getConnection(url);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    public void close() {
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }

    public static void main(String[] args) {
        try {
            String sql = "/*#mycat:db_type=master*/select 1";
            JDBC jdbc = new JDBC(sql);
            ResultSet ret = jdbc.pst.executeQuery();//执行语句，得到结果集
            while (ret.next()) {
                String uid = ret.getString(1);
                /*String ufname = ret.getString(2);
                String ulname = ret.getString(3);
                String udate = ret.getString(4);*/
                System.out.println(uid);
            }//显示数据
            ret.close();
            jdbc.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}  