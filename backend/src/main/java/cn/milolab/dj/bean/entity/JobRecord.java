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
public class JobRecord {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private Integer jobId;
    private Integer employeeId;
    private Boolean present;
    private Date startTime;
    private Date endTime;

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
                Objects.equals(employeeId, jobRecord.employeeId) &&
                Objects.equals(present, jobRecord.present) &&
                DateUtil.equals(startTime, jobRecord.startTime) &&
                DateUtil.equals(endTime, jobRecord.endTime);
    }
}
