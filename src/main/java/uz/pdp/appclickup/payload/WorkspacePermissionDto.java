package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.enums.WorkspacePermissionName;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class WorkspacePermissionDto {
    @NotNull
    private UUID workspaceRoleId;

    @NotNull
    private WorkspacePermissionName permission;

}
