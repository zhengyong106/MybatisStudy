package xyz.wecloud.mybatis.spring.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定义一个切面，用于在标注了 @DataSource 注解的方法调用前动态切换数据源
 * @Order 表示设置aop执行顺序，数字越小越被先执行，这里主要是为了防止注解方法开启了事务后事务先于切换数据源执行而导致数据源切换失败的问题，
 **/
@Aspect
@Order(0)
@Component
public class DataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Before("@annotation(xyz.wecloud.mybatis.spring.datasource.DataSource)")
    public void setDataSource2test01(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        DataSourceType dataSourceType = method.getAnnotation(DataSource.class).value();
        DataSourceTypeHolder.setDataBaseType(dataSourceType);
        logger.info("已切换数据源: {}", dataSourceType);
    }
}
