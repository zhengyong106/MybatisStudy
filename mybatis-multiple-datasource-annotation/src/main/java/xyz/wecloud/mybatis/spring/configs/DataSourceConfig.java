package xyz.wecloud.mybatis.spring.configs;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import xyz.wecloud.mybatis.spring.datasource.DataSourceType;
import xyz.wecloud.mybatis.spring.datasource.DynamicDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:datasource.properties")
@MapperScan("xyz.wecloud.mybatis.spring.mappers")
public class DataSourceConfig {

    /**
     * 配置两个真的需要用到的数据源
     * */
    @Bean
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

    /**
     * 配置动态数据源，我们可以通过继承 AbstractRoutingDataSource 类实现，
     * 其中我们可以通过设置 targetDataSources 属性（Map 类型，其中 key 表示每个数据源的唯一标识，value 表示数据源）定义多数据源，
     * 并在真正访问操作数据源之前调用 determineCurrentLookupKey（）方法动态获取需要切换的数据源的唯一标识
     **/
    @Bean
    public DataSource dynamicDataSource(@Qualifier("dataSource1") DataSource dataSource1, @Qualifier("dataSource2") DataSource dataSource2){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.DATA_BASE_1, dataSource1);
        targetDataSources.put(DataSourceType.DATA_BASE_2, dataSource2);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSource1);
        return dataSource;
    }

    /**
     * 指定 sqlSessionFactory 引用动态数据源
     **/
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }
}
