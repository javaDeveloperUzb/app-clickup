package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.enums.TaskPermission;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ProjectUserDTO {
    @NotNull
    private Long projectId;

    @NotNull
    private UUID userId;

    @NotNull
    private TaskPermission taskPermission;

    @NotNull
    private Long workspaceId;


}
