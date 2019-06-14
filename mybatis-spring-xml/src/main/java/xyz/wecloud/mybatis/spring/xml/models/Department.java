package xyz.wecloud.mybatis.spring.xml.models;

import java.util.Date;

public class Department {
    private Integer depId;
    private String depName;
    private Date depCTime;

    public Department() {
    }

    public Department(Integer depId, String depName, Date depCTime) {
        this.depId = depId;
        this.depName = depName;
        this.depCTime = depCTime;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Date getDepCTime() {
        return depCTime;
    }

    public void setDepCTime(Date depCTime) {
        this.depCTime = depCTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("depId=").append(depId);
        sb.append(", depName='").append(depName).append('\'');
        sb.append(", depCTime=").append(depCTime);
        sb.append('}');
        return sb.toString();
    }
}
