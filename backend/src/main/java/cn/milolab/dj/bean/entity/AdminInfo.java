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

}
