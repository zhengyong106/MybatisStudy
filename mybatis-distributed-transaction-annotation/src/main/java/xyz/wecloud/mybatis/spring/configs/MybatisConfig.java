package xyz.wecloud.mybatis.spring.configs;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "xyz.wecloud.mybatis.spring.mappers.db1", sqlSessionFactoryRef = "sqlSessionFactory1")
@MapperScan(value = "xyz.wecloud.mybatis.spring.mappers.db2", sqlSessionFactoryRef = "sqlSessionFactory2")
public class MybatisConfig {
    @Bean
    @Primary
    public SqlSessionFactoryBean sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }
}
