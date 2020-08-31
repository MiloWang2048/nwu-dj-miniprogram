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
public class Employee {
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
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(deleted, employee.deleted) &&
                DateUtil.equals(cstCreate, employee.cstCreate) &&
                DateUtil.equals(cstModified, employee.cstModified) &&
                Objects.equals(userId, employee.userId) &&
                Objects.equals(stuSerial, employee.stuSerial) &&
                Objects.equals(jobSerial, employee.jobSerial) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(role, employee.role);
    }

}
