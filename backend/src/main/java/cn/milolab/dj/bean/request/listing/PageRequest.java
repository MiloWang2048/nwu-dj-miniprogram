package cn.milolab.dj.bean.request.listing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    @Positive
    private Integer page = 1;

    @Min(10)
    private Integer size = 10;
}
