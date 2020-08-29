package cn.milolab.dj.bean.request.listing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateFilterRequest {
    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}
