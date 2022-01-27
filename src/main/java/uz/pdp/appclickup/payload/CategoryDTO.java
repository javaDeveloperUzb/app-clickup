package uz.pdp.appclickup.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long projectId;

    private String accessType = "PUBLIC";

    private boolean archived;

    private String color = "#1111";

    private Long workspaceId;

}
