package jdbc;

import java.sql.*;

/**
 * Created by admin on 2017/10/19.
 */
public class DBTest {
    public static Connection getJDBCConnection(){
//        String URL = "jdbc:mysql://172.27.137.12:3306/yadmin?useUnicode=true&amp;characterEncoding=utf8";
//        String URL = "jdbc:mysql://116.31.122.23:3306/yadmin?useUnicode=true&amp;characterEncoding=utf8";
        String URL = "jdbc:mysql://mysql.local:3306/test?useUnicode=true&amp;characterEncoding=utf8";
//        String URL = "jdbc:mysql://192.168.5.118:3306/mdd?useUnicode=true&amp;characterEncoding=utf8";
        Connection conn = null;
//        String username = "javaer";
//        String username = "tvbar";
        String username = "root";
//        String password = "javaer";
//        String password = "duowan";
        String password = "root";
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

    public void select(int seconds){
        String sql ="select sleep("+seconds+")";
        Connection conn = getJDBCConnection();
        PreparedStatement pstmt = null;
        ResultSet ret = null;
        try {
            pstmt = conn.prepareStatement(sql);
            ret = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
    public static void insert(){
        String sql ="inset into t2 (uuid,name) values(?,?)";
        Connection conn = getJDBCConnection();
        PreparedStatement pstmt = null;
        ResultSet ret = null;

        try {
            pstmt = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                pstmt.setString(1,"uuid"+i);
                pstmt.setString(1,"name"+i);
                pstmt.execute();
                pstmt.clearParameters();
            }
            System.out.println("it consumes " +(System.currentTimeMillis() - start) + "ms");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws SQLException {
        insert();
        /*Connection conn = getJDBCConnection();
        String sql ="select * from gray_user";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet ret = pstmt.executeQuery();
        while (ret.next()) {
            String uid = ret.getString(1);
                *//*String ufname = ret.getString(2);
                String ulname = ret.getString(3);
                String udate = ret.getString(4);*//*
            System.out.println(uid);
        }*///显示数据

    }
}
