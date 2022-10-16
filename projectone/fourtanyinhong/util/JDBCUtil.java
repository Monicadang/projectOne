package com.stx.exercise.p9.fourtanyinhong.util;

import java.sql.*;

/**
 * ClassName:JDBCUtil
 * Package:com.stx.exercise.p12
 * Description: JDBC工具类
 * @Date:2022/10/11 15:09
 * @Author:tanyinhong
 */
public class JDBCUtil {

    private static String driverName = "oracle.jdbc.OracleDriver";
    private static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static String user = "scott";
    private static String password = "scott";
    private static JDBCUtil instance= null;
    static{
        try {
            //注册驱动
            Class.forName(driverName);
            instance = new JDBCUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //private JDBCUtil(){}

    public static JDBCUtil getInstance(){
        return instance;
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection(){
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     * @param rs
     * @param st
     * @param conn
     */
    public void close(ResultSet rs, Statement st,Connection conn){
        try {
            if (rs != null)rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (st != null)st.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null)conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
