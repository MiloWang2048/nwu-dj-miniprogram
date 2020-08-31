package cn.milolab.dj.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeJobRequest {
    @Positive
    private Integer jobId;

    @Positive
    private Integer targetEmployeeId;
}
