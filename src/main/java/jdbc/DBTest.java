package jdbc;

import java.sql.*;

/**
 * Created by admin on 2017/10/19.
 */
public class DBTest {
    public static Connection getJDBCConnection(){
//        String URL = "jdbc:mysql://172.27.137.12:3306/yadmin?useUnicode=true&amp;characterEncoding=utf8";
//        String URL = "jdbc:mysql://116.31.122.23:3306/yadmin?useUnicode=true&amp;characterEncoding=utf8";
//        String URL = "jdbc:mysql://192.168.10.204:3306/db1?useUnicode=true&amp;characterEncoding=utf8";
        String URL = "jdbc:mysql://10.10.50.107:3306/yunnexadmin?useUnicode=true&amp;characterEncoding=utf8";
        Connection conn = null;
//        String username = "javaer";
//        String username = "tvbar";
        String username = "root";
//        String password = "javaer";
//        String password = "duowan";
        String password = "yunnex6j7";
        try {
            //加载驱动
            Driver.class.forName("com.mysql.jdbc.Driver");
            //建立连接
            conn = DriverManager.getConnection(URL, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = getJDBCConnection();
        String sql ="select * from gray_user";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet ret = pstmt.executeQuery();
        while (ret.next()) {
            String uid = ret.getString(1);
                /*String ufname = ret.getString(2);
                String ulname = ret.getString(3);
                String udate = ret.getString(4);*/
            System.out.println(uid);
        }//显示数据

    }
}
