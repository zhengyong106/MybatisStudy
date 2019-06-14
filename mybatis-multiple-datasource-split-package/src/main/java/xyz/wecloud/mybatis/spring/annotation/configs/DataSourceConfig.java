package xyz.wecloud.mybatis.spring.annotation.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {
    @Bean
    @Primary
    public DataSource dataSource1(@Autowired Environment environment){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc1.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc1.url"));
        dataSource.setUsername(environment.getProperty("jdbc1.username"));
        dataSource.setPassword(environment.getProperty("jdbc1.password"));
        return dataSource;
    }

    @Bean
    public DataSource dataSource2(@Autowired Environment environment){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc2.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc2.url"));
        dataSource.setUsername(environment.getProperty("jdbc2.username"));
        dataSource.setPassword(environment.getProperty("jdbc2.password"));
        return dataSource;
    }
}
