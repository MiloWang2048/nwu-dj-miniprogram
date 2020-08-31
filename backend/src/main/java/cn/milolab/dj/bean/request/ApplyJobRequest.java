package cn.milolab.dj.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.util.Date;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyJobRequest {
    @Positive
    private Integer jobId;

    @Positive
    private Integer employeeId;

    @Future
    private Date startTime;

    @Future
    private Date endTime;
}
