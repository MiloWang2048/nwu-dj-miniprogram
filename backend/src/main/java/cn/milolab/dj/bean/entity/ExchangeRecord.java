package cn.milolab.dj.bean.entity;

import cn.milolab.dj.util.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author milowang
 */
public class ExchangeRecord {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private Integer jobId;
    private Integer originalUserId;
    private Integer targetUserId;
    private Boolean accepted;

    public ExchangeRecord() {
    }

    public ExchangeRecord(Integer id, Boolean deleted, Date cstCreate, Date cstModified, Integer jobId, Integer originalUserId, Integer targetUserId, Boolean accepted) {
        this.id = id;
        this.deleted = deleted;
        this.cstCreate = cstCreate;
        this.cstModified = cstModified;
        this.jobId = jobId;
        this.originalUserId = originalUserId;
        this.targetUserId = targetUserId;
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExchangeRecord that = (ExchangeRecord) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deleted, that.deleted) &&
                DateUtil.equals(cstCreate, that.cstCreate) &&
                DateUtil.equals(cstModified, that.cstModified) &&
                Objects.equals(jobId, that.jobId) &&
                Objects.equals(originalUserId, that.originalUserId) &&
                Objects.equals(targetUserId, that.targetUserId) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, cstCreate, cstModified, jobId, originalUserId, targetUserId, accepted);
    }

    @Override
    public String toString() {
        return "ExchangeRecord{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", cstCreate=" + cstCreate +
                ", cstModified=" + cstModified +
                ", jobId=" + jobId +
                ", originalUserId='" + originalUserId + '\'' +
                ", targetUserId='" + targetUserId + '\'' +
                ", accepted=" + accepted +
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

    public Integer getOriginalUserId() {
        return originalUserId;
    }

    public void setOriginalUserId(Integer originalUserId) {
        this.originalUserId = originalUserId;
    }

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
