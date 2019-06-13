package xyz.wecloud.mybatis.models;

import java.util.List;

public class EmployeeCustom extends Employee {
    private List<Card> empCards;
    private Department department;

    public EmployeeCustom() {
        super();
    }

    public List<Card> getEmpCards() {
        return empCards;
    }

    public void setEmpCards(List<Card> empCards) {
        this.empCards = empCards;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + getEmpId() +
                ", empName='" + getEmpName() + '\'' +
                ", empSalary=" + getEmpSalary() +
                ", empAge=" + getEmpAge() +
                ", empCards=" + empCards +
                ", department=" + department +
                '}';
    }
}
