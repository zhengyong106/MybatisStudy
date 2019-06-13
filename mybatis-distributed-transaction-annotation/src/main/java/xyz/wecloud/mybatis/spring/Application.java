package xyz.wecloud.mybatis.spring;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import xyz.wecloud.mybatis.spring.models.Department;
import xyz.wecloud.mybatis.spring.models.Employee;
import xyz.wecloud.mybatis.spring.configs.ApplicationConfig;
import xyz.wecloud.mybatis.spring.services.ApplicationService;

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
    public void testSelect(){
        Department department = applicationService.getDepartmentByEmployeeId(1);
        logger.info(String.valueOf(department));
    }

    @Test
    public void testSave(){
        Employee employee = new Employee();
        employee.setEmpName("老赵");
        employee.setEmpAge(42);
        employee.setEmpSalary(4500.);

        Department department = new Department();
        department.setDepName("外交部");
        department.setDepCTime(new Date());

        applicationService.saveEmployeeAndDepartment(employee, department);
    }
}
