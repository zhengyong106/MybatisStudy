package xyz.wecloud.mybatis.spring.annotation.configs;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;

@Configuration
public class TransactionManagerConfig {
    /**
     * 配置 TransactionManager 用于spring容器的事务管理，其底层基于aop 实现，可以通过XML，注解，api 方法指定事务管理的切入点
     * */
    @Bean
    public PlatformTransactionManager transactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 配置 TransactionInterceptor 即事务拦截器用于对务的方法进行拦截，并使用事务管理器管理事务
     * 这里已经被注释掉因为可以通过 @EnableTransactionManagement 和 @Transactional 进行更快更方便的事务管理
     * */
    //@Bean
    public TransactionInterceptor transactionInterceptor(@Autowired PlatformTransactionManager transactionManager) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(transactionManager);
        NameMatchTransactionAttributeSource attributeSource = new NameMatchTransactionAttributeSource();

        // 创建四个不同的 RuleBasedTransactionAttribute 对象，分别对应 service 方法中的增删改查操作，然后制定不同的事物管理策略
        RuleBasedTransactionAttribute queryAttribute = new RuleBasedTransactionAttribute();
        queryAttribute.setReadOnly(true);
        RuleBasedTransactionAttribute saveAttribute = new RuleBasedTransactionAttribute();
        RuleBasedTransactionAttribute removeAttribute = new RuleBasedTransactionAttribute();
        RuleBasedTransactionAttribute updateAttribute = new RuleBasedTransactionAttribute();
        // 通过简单通配符对 service 中的方法名进行通配，符合通配条件的则进行事务管理
        attributeSource.addTransactionalMethod("get*", queryAttribute);
        attributeSource.addTransactionalMethod("save*", saveAttribute);
        attributeSource.addTransactionalMethod("remove", removeAttribute);
        attributeSource.addTransactionalMethod("update*", updateAttribute);

        transactionInterceptor.setTransactionAttributeSource(attributeSource);
        return transactionInterceptor;
    }

    /**
     * 配置 BeanNameAutoProxyCreator 用于对指定目标（Bean）自动创建代理对象，相当于创建一个事务切面，并设置被代理目标为切入点，所执行的代理方法为切面的通知
     * 这里已经被注释掉因为通过 @EnableTransactionManagement 和 @Transactional 可以跟快更方便的进行事务管理
     * */
    //@Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        // 设置是否直接代理目标类，而不仅仅是代理特定接口。默认值为“false”
        transactionAutoProxy.setProxyTargetClass(true);
        // 通过简单通配符对 beanName 进行匹配，判断是否代理目标类
        transactionAutoProxy.setBeanNames("*Service");
        // 通过简单通配符对 interceptorName 进行匹配
        transactionAutoProxy.setInterceptorNames("transactionInterceptor");
        return transactionAutoProxy;
    }
}
