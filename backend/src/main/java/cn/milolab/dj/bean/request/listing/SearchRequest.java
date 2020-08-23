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
public class SearchRequest {
    @NotBlank
    private String searchField;
    @NotBlank
    private String searchValue;
}
