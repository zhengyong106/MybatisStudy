package xyz.wecloud.mybatis.spring.configs;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.wecloud.mybatis.spring.mappers.DepartmentMapper;
import xyz.wecloud.mybatis.spring.mappers.EmployeeMapper;

import javax.sql.DataSource;

@Configuration
@MapperScan("xyz.wecloud.mybatis.spring.mappers")
public class MybatisConfig {
    /**
     * 配置 SqlSessionFactoryBean
     * */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory1(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }

    /**
     * 配置 MapperScannerConfigurer 可用于指定要扫描的 Mapper 映射器的包的路径
     * 这里已经被注释掉因为已经使用 @MapperScan 开启了 Mapper 映射器自动扫描，无需再手动添加
     * */
    //@Bean
    public MapperScannerConfigurer scannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("xyz.wecloud.mybatis.spring.mappers");
        return mapperScannerConfigurer;
    }

    /**
     * 配置 MapperFactoryBean 用于生产Mapper映射器代理实例
     * 这里已经被注释掉因为已经使用 @MapperScan 开启了 Mapper 映射器自动扫描，无需再手动添加
     * */
    //@Bean
    public MapperFactoryBean employeeMapper(@Autowired SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean employeeMapper = new MapperFactoryBean();
        employeeMapper.setMapperInterface(EmployeeMapper.class);
        employeeMapper.setSqlSessionFactory(sqlSessionFactory);
        return employeeMapper;
    }
    //@Bean
    public MapperFactoryBean departmentMapper(@Autowired SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean employeeMapper = new MapperFactoryBean();
        employeeMapper.setMapperInterface(DepartmentMapper.class);
        employeeMapper.setSqlSessionFactory(sqlSessionFactory);
        return employeeMapper;
    }
}
