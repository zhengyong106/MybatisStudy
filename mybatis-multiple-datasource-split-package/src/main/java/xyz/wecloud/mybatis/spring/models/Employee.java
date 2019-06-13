package xyz.wecloud.mybatis.spring.models;

public class Employee {
    private Integer empId;
    private String empName;
    private Double empSalary;
    private Integer empAge;
    private Integer depId;

    public Employee() {
    }

    public Employee(Integer empId, String empName, Double empSalary, Integer empAge, Integer depId) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
        this.depId = depId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("empId=").append(empId);
        sb.append(", empName='").append(empName).append('\'');
        sb.append(", empSalary=").append(empSalary);
        sb.append(", empAge=").append(empAge);
        sb.append(", depId=").append(depId);
        sb.append('}');
        return sb.toString();
    }
}
