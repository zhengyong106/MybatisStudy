package xyz.wecloud.mybatis.spring;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.wecloud.mybatis.spring.annotation.configs.ApplicationConfig;
import xyz.wecloud.mybatis.spring.annotation.models.Department;
import xyz.wecloud.mybatis.spring.annotation.models.Employee;
import xyz.wecloud.mybatis.spring.annotation.services.ApplicationService;

import java.util.Date;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private ApplicationContext context;
    private ApplicationService applicationService;

    @Before
    public void setUp() {
        // 使用AnnotationConfigApplicationContext可以实现基于Java的配置类加载Spring的应用上下文。避免使用application.xml进行配置。相比XML配置，更加便捷。
        context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        applicationService = context.getBean(ApplicationService.class);
    }

    @Test
    public void testSave1(){
        Employee employee = new Employee();
        employee.setEmpName("老刘");
        employee.setEmpAge(47);
        employee.setEmpSalary(6500.);
        applicationService.saveEmployee(employee);
    }

    @Test
    public void testSave2(){
        Department department = new Department();
        department.setDepName("外交部");
        department.setDepCTime(new Date());
        applicationService.saveDepartment(department);
    }
}
