package xyz.wecloud.mybatis.spring.configs;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.UserTransaction;

@Configuration
public class TransactionManagerConfig {
    /**
     * 配置 TransactionManager 用于spring容器的事务管理，其底层基于aop 实现，可以通过XML，注解等方法配置指定需要执行事务管理的方法
     * */
    @Bean
    public PlatformTransactionManager transactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
