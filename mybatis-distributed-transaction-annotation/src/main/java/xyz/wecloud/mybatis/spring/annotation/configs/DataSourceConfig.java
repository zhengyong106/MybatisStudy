package xyz.wecloud.mybatis.spring.annotation.configs;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {
    @Bean
    @Primary
    public AtomikosDataSourceBean dataSource1(@Autowired Environment environment){
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        dataSource.setUniqueResourceName("dataSource111");
        dataSource.setPoolSize(5);
        dataSource.setXaProperties(build("jdbc1.", environment));
        return dataSource;
    }

    @Bean
    public AtomikosDataSourceBean dataSource2(@Autowired Environment environment){
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        dataSource.setUniqueResourceName("dataSource222");
        dataSource.setPoolSize(5);
        dataSource.setXaProperties(build("jdbc2.", environment));
        return dataSource;
    }

    private Properties build(String prefix, Environment env) {
        Properties prop = new Properties();
        prop.put("url", Objects.requireNonNull(env.getProperty(prefix + "url")));
        prop.put("username", Objects.requireNonNull(env.getProperty(prefix + "username")));
        prop.put("password", Objects.requireNonNull(env.getProperty(prefix + "password")));
        prop.put("driverClassName", Objects.requireNonNull(env.getProperty(prefix + "driverClassName")));
        return prop;
    }
}
