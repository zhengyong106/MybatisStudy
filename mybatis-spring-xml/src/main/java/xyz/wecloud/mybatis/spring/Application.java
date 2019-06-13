package xyz.wecloud.mybatis.spring;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.wecloud.mybatis.spring.models.Department;
import xyz.wecloud.mybatis.spring.models.Employee;
import xyz.wecloud.mybatis.spring.services.ApplicationService;

import java.util.Date;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private ApplicationContext context;
    private ApplicationService applicationService;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("classpath:config/applicationContext.xml");
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
        employee.setEmpName("老刘");
        employee.setEmpAge(47);
        employee.setEmpSalary(6500.);

        Department department = new Department();
        department.setDepName("外交部");
        department.setDepCTime(new Date());

        applicationService.saveEmployeeAndDepartment(employee, department);
    }
}
