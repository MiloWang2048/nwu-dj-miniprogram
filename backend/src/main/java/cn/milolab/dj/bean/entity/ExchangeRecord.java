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
public class ExchangeRecord {
    private Integer id;
    private Boolean deleted;
    private Date cstCreate;
    private Date cstModified;
    private Integer jobId;
    private Integer originalEmployeeId;
    private Integer targetEmployeeId;
    private Boolean accepted;

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
                Objects.equals(originalEmployeeId, that.originalEmployeeId) &&
                Objects.equals(targetEmployeeId, that.targetEmployeeId) &&
                Objects.equals(accepted, that.accepted);
    }
}
