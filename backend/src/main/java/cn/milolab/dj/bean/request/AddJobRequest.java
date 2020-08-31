package cn.milolab.dj.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddJobRequest {
    @NotBlank
    @Size(min = 1, max = 255)
    private String name;

    @NotBlank
    @Size(min = 1, max = 255)
    private String jobPosition;

    @FutureOrPresent
    @Size(min = 1, max = 255)
    private Date startTime;

    @Future
    private Date endTime;

    @Positive
    private Integer maxJob;
}
