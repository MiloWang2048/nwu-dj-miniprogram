package cn.milolab.dj.bean.entity;

import cn.milolab.dj.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
