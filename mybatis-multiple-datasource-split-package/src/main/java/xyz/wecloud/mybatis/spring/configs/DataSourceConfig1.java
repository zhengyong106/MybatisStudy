package xyz.wecloud.mybatis.spring.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
@MapperScan(value = "xyz.wecloud.mybatis.spring.mappers.db1", sqlSessionFactoryRef = "sqlSessionFactory1")
public class DataSourceConfig1{
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
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }
}
