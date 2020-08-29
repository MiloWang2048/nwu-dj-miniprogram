package cn.milolab.dj.bean.request.listing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author milowang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    @NotBlank
    @Size(min = 1, max = 255)
    private String searchField;

    @NotBlank
    @Size(min = 1, max = 255)
    private String searchValue;
}
