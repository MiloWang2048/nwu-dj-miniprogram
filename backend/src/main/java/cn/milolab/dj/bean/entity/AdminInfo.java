package cn.milolab.dj.bean.entity;

import cn.milolab.dj.util.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author milowang
 */
public class AdminInfo {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private Integer userId;
    private String stuSerial;
    private String jobSerial;
    private String name;
    private String role;

    public AdminInfo(Integer id, Boolean deleted, Date cstCreate, Date cstModified, Integer userId,
                     String stuSerial,
                     String jobSerial, String name, String role) {
        this.id = id;
        this.deleted = deleted;
        this.cstCreate = cstCreate;
        this.cstModified = cstModified;
        this.userId = userId;
        this.stuSerial = stuSerial;
        this.jobSerial = jobSerial;
        this.name = name;
        this.role = role;
    }

    public AdminInfo() {
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", cstCreate=" + cstCreate +
                ", cstModified=" + cstModified +
                ", userId=" + userId +
                ", stuSerial='" + stuSerial + '\'' +
                ", jobSerial='" + jobSerial + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdminInfo adminInfo = (AdminInfo) o;
        return Objects.equals(id, adminInfo.id) &&
                Objects.equals(deleted, adminInfo.deleted) &&
                DateUtil.equals(cstCreate, adminInfo.cstCreate) &&
                DateUtil.equals(cstModified, adminInfo.cstModified) &&
                Objects.equals(userId, adminInfo.userId) &&
                Objects.equals(stuSerial, adminInfo.stuSerial) &&
                Objects.equals(jobSerial, adminInfo.jobSerial) &&
                Objects.equals(name, adminInfo.name) &&
                Objects.equals(role, adminInfo.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, cstCreate, cstModified, userId, stuSerial, jobSerial, name, role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCstCreate() {
        return cstCreate;
    }

    public void setCstCreate(Date cstCreate) {
        this.cstCreate = cstCreate;
    }

    public Date getCstModified() {
        return cstModified;
    }

    public void setCstModified(Date cstModified) {
        this.cstModified = cstModified;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStuSerial() {
        return stuSerial;
    }

    public void setStuSerial(String stuSerial) {
        this.stuSerial = stuSerial;
    }

    public String getJobSerial() {
        return jobSerial;
    }

    public void setJobSerial(String jobSerial) {
        this.jobSerial = jobSerial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
