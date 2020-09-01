package cn.milolab.dj.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author milow
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyRequest {
    @NotBlank
    @Size(min = 1, max = 255)
    private String stuSerial;

    @NotBlank
    @Size(min = 1, max = 255)
    private String jobSerial;
}
