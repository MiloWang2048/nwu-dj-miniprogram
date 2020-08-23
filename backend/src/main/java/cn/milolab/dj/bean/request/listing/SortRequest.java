package cn.milolab.dj.bean.request.listing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortRequest {
    @NotBlank
    private String sortField;
    private Boolean descent = false;
}
