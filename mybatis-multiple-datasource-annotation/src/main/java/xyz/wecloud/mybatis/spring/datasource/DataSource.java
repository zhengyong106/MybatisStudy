package xyz.wecloud.mybatis.spring.datasource;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 定义注解，用于配合 AOP 和注解实现动态数据源切换
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSource {
    @AliasFor("dataSourceType")
    DataSourceType value() default DataSourceType.DATA_SOURCE_1;
}
