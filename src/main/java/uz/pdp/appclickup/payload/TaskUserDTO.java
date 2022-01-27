package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.enums.AddType;

import java.util.UUID;

@Data
public class TaskUserDTO {
    private UUID id;

    private UUID taskId;

    private UUID userId;

    private AddType addType;

    private Long workspaceId;

}
