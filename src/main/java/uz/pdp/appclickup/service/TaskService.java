package uz.pdp.appclickup.service;


import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.TaskAttachmentDTO;
import uz.pdp.appclickup.payload.TaskDTO;
import uz.pdp.appclickup.payload.TaskUserDTO;

import java.util.UUID;

public interface TaskService {
    ApiResponse addOrEditTask(TaskDTO taskDTO, User user);

    ApiResponse deleteTask(UUID taskId, User user);

    ApiResponse changeStatus(Long statusId, UUID taskId, User user, Long wId);

    ApiResponse addOrDeleteTaskAttachment(TaskAttachmentDTO taskAttachmentDTO, User user);

    ApiResponse addOrDeleteTaskUser(TaskUserDTO taskUserDTO, User user);

}
