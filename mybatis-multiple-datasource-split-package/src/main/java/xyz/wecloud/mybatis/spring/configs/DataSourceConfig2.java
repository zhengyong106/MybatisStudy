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
@MapperScan(value = "xyz.wecloud.mybatis.spring.mappers.db2", sqlSessionFactoryRef = "sqlSessionFactory2")
public class DataSourceConfig2{
    @Bean
    public DataSource dataSource2(@Autowired Environment environment){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc2.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc2.url"));
        dataSource.setUsername(environment.getProperty("jdbc2.username"));
        dataSource.setPassword(environment.getProperty("jdbc2.password"));
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }
}
