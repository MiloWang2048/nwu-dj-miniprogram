package cn.milolab.dj.bean.entity;

import cn.milolab.dj.util.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author milowang
 */
public class Job {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private String name;
    private String jobPosition;
    private Date startTime;
    private Date endTime;
    private Integer maxJob;

    public Job() {
    }

    public Job(Integer id, Boolean deleted, Date cstCreate, Date cstModified, String name, String jobPosition, Date startTime, Date endTime, Integer maxJob) {
        this.id = id;
        this.deleted = deleted;
        this.cstCreate = cstCreate;
        this.cstModified = cstModified;
        this.name = name;
        this.jobPosition = jobPosition;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxJob = maxJob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Job job = (Job) o;
        return Objects.equals(id, job.id) &&
                Objects.equals(deleted, job.deleted) &&
                DateUtil.equals(cstCreate, job.cstCreate) &&
                DateUtil.equals(cstModified, job.cstModified) &&
                Objects.equals(name, job.name) &&
                Objects.equals(jobPosition, job.jobPosition) &&
                DateUtil.equals(startTime, job.startTime) &&
                DateUtil.equals(endTime, job.endTime) &&
                Objects.equals(maxJob, job.maxJob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deleted, cstCreate, cstModified, name, jobPosition, startTime, endTime, maxJob);
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", deleted=" + deleted +
                ", cstCreate=" + cstCreate +
                ", cstModified=" + cstModified +
                ", name='" + name + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", maxJob=" + maxJob +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
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

    public Integer getMaxJob() {
        return maxJob;
    }

    public void setMaxJob(Integer maxJob) {
        this.maxJob = maxJob;
    }
}
