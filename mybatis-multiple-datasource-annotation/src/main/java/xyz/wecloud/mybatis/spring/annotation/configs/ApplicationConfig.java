package xyz.wecloud.mybatis.spring.annotation.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @EnableAspectJAutoProxy 表示开启 AOP 代理自动配置
 * @ComponentScan 可以指定需要扫描到 Spring 容器中的 Comment 组件路径
 * @EnableTransactionManagement 可以开启Spring容器的事务支持，通过在 Service 类的方法上添加 @Transactional 注解即可开启事务，或者可以在 xml 文件中配置具体事务切入点和通知。
 * */
@EnableAspectJAutoProxy
@ComponentScan("xyz.wecloud.mybatis.spring")
@EnableTransactionManagement
public class ApplicationConfig {

}
