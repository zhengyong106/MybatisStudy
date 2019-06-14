package xyz.wecloud.mybatis.spring.annotation.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TransactionManagerConfig {
    /**
     * 针对多数据源事务管理，我们可以创建多个 TransactionManager 事务管理器，并为不同的事务管理器绑定不同的 DataSource 数据源
     * 这样我们就可以在 Spring 事务管理中指定不同的事务管理器管理多数据源
     **/
    @Bean
    @Primary
    public PlatformTransactionManager transactionManager1(@Qualifier("dataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PlatformTransactionManager transactionManager2(@Qualifier("dataSource2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
