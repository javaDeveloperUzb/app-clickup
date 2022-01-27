package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.TaskAttachmentDTO;
import uz.pdp.appclickup.payload.TaskDTO;
import uz.pdp.appclickup.payload.TaskUserDTO;
import uz.pdp.appclickup.security.CurrentUser;
import uz.pdp.appclickup.service.TaskService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;


    @PostMapping
    public HttpEntity<?> addOrEditTask(@Valid @RequestBody TaskDTO taskDTO, @CurrentUser User user) {
        ApiResponse apiResponse = taskService.addOrEditTask(taskDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping
    public HttpEntity<?> deleteTask(@RequestParam UUID taskId, @CurrentUser User user) {
        ApiResponse apiResponse = taskService.deleteTask(taskId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> changeStatus(@RequestParam Long statusId, @RequestParam UUID taskId, @CurrentUser User user, @RequestParam Long wId) {
        ApiResponse apiResponse = taskService.changeStatus(statusId, taskId, user, wId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> addOrDeleteTaskAttachment(@Valid @RequestBody TaskAttachmentDTO taskAttachmentDTO, @CurrentUser User user) {
        ApiResponse apiResponse = taskService.addOrDeleteTaskAttachment(taskAttachmentDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping
    public HttpEntity<?> addOrDeleteTaskUser(@Valid @RequestBody TaskUserDTO taskUserDTO, @CurrentUser User user) {
        ApiResponse apiResponse = taskService.addOrDeleteTaskUser(taskUserDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }

}
