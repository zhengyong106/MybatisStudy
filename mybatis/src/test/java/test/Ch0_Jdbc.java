package test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class Ch0_Jdbc {
    private static Logger logger = LoggerFactory.getLogger(Ch0_Jdbc.class);

    @Test
    public void findUserById() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try (InputStream inputStream = Ch0_Jdbc.class.getClassLoader().getResourceAsStream("datasource.properties")) {
            // 创建Properties对象，然后加载配置文件，最后读取配置文件中的配置值
            Properties dataSource = new Properties();
            dataSource.load(inputStream);
            String driver = dataSource.getProperty("jdbc.driverClassName");
            String url = dataSource.getProperty("jdbc.url");
            String username = dataSource.getProperty("jdbc.username");
            String password = dataSource.getProperty("jdbc.password");
            // 实例化数据库驱动
            Class.forName(driver);
            // driverManager获取连接
            connection = DriverManager.getConnection(url, username, password);
            // 通过connection获取会话
            statement = connection.createStatement();
            // 执行select
            resultSet = statement.executeQuery("SELECT * FROM TAB_EMP");
            // 打印结果集
            logger.info("输出结果集[{}]", resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
