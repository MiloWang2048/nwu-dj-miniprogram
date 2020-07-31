package cn.milolab.dj.bean.entity;

import cn.milolab.dj.util.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author milowang
 */
public class JobRecord {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private Integer jobId;
    private Integer userId;
    private Boolean present;
    private Date startTime;
    private Date endTime;

    public JobRecord() {
    }

    public JobRecord(Integer id, Boolean deleted, Date cstCreate, Date cstModified, Integer jobId, Integer userId, Boolean present, Date startTime, Date endTime) {
        this.id = id;
        this.deleted = deleted;
        this.cstCreate = cstCreate;
        this.cstModified = cstModified;
        this.jobId = jobId;
        this.userId = userId;
        this.present = present;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobRecord jobRecord = (JobRecord) o;
        return Objects.equals(id, jobRecord.id) &&
                Objects.equals(deleted, jobRecord.deleted) &&
                DateUtil.equals(cstCreate, jobRecord.cstCreate) &&
                DateUtil.equals(cstModified, jobRecord.cstModified) &&
                Objects.equals(jobId, jobRecord.jobId) &&
                Objects.equals(userId, jobRecord.userId) &&
                Objects.equals(present, jobRecord.present) &&
                DateUtil.equals(startTime, jobRecord.startTime) &&
                DateUtil.equals(endTime, jobRecord.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, cstCreate, cstModified, jobId, userId, present, startTime, endTime);
    }

    @Override
    public String toString() {
        return "JobRecord{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", cstCreate=" + cstCreate +
                ", cstModified=" + cstModified +
                ", jobId=" + jobId +
                ", userId=" + userId +
                ", present=" + present +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
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

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
