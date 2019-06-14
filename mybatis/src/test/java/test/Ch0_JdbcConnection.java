package test;

import org.apache.ibatis.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class Ch0_JdbcConnection {
    private static Logger logger = LoggerFactory.getLogger(Ch0_JdbcConnection.class);

    @Test
    public void findUserById() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try (InputStream inputStream = Resources.getResourceAsStream("datasource.properties")) {
            // 读取配置文件，并创建 Properties 对象
            Properties prop = new Properties();
            prop.load(inputStream);
            String driver = prop.getProperty("jdbc.driverClassName");
            String url = prop.getProperty("jdbc.url");
            String username = prop.getProperty("jdbc.username");
            String password = prop.getProperty("jdbc.password");
            // 加载数据库驱动类，并执行其静态代码块
            Class.forName(driver);
            // 通过 DriverManager 获取到数据库连接
            connection = DriverManager.getConnection(url, username, password);
            // 通过连接获取 Statement
            statement = connection.createStatement();
            // 执行 Select 语句
            resultSet = statement.executeQuery("SELECT * FROM TAB_EMP");
            // 打印结果集
            logger.info("输出结果集[{}]", resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
