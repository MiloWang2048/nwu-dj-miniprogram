package cn.milolab.dj.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDetailRequest {
    @NonNull
    @Positive
    private Integer jobId;
}
