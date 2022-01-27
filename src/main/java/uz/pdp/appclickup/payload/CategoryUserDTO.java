package uz.pdp.appclickup.payload;

import lombok.Data;
import uz.pdp.appclickup.entity.enums.TaskPermission;

import java.util.UUID;

@Data
public class CategoryUserDTO {
    private String name;

    private Long categoryId;

    private UUID userId;

    private TaskPermission taskPermission;
}
