package top.lovelc.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    // 定义静态变量，使用静态代码获取配置文件的值
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    // 使用静态代码注册驱动并给静态变量赋值
    static{
        try {
            // 创建Properties集合类
            Properties pro = new Properties();
            // 获取src路径下文件，使用ClassLoader类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            // URL定位了文件的绝对路径
            URL res = classLoader.getResource("jdbc.properties");
            // 获取字符串路径
            String path = res.getPath();
            // 读取文件
            pro.load(new FileReader(path));
            // 给静态变量赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            // 注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    /**
     * @Author ZhaoPeiXuan
     * @Description 释放资源
     * @Date 22:08 2019/9/16
     * @Param [stmt 执行sql的对象, conn 数据库连接对象]
     * @return void
     **/
    public static void close(Statement stmt, Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * @Author ZhaoPeiXuan
     * @Description 释放资源Pro版
     * @Date 22:13 2019/9/16
     * @Param [rs 结果集对象, stmt 执行sql的对象, conn 数据库连接对象]
     * @return void
     **/
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
