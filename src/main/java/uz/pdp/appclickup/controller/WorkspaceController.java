package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.MemberDto;
import uz.pdp.appclickup.payload.WorkspaceDto;
import uz.pdp.appclickup.payload.WorkspaceRoleDto;
import uz.pdp.appclickup.security.CurrentUser;
import uz.pdp.appclickup.service.WorkspaceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {
    @Autowired
    WorkspaceService workspaceService;

    @PostMapping
    public HttpEntity<?> addWorkspace(@Valid @RequestBody WorkspaceDto workspaceDto, @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDto, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/role")
    public HttpEntity<?> addRole(@RequestParam Long workspaceId,
                                 @RequestBody WorkspaceRoleDto workspaceRoleDto,
                                 @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.addRole(workspaceId, workspaceRoleDto, user);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping()
    public HttpEntity<?> editWorkspace(@RequestBody WorkspaceDto workspaceDto) {
        ApiResponse apiResponse = workspaceService.editWorkspace(workspaceDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/changeOwner/{id}")
    public HttpEntity<?> changeOwnerWorkspace(@PathVariable Long id,
                                              @RequestParam UUID ownerId,
                                              @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.changeOwnerWorkspace(id, ownerId, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWorkspace(@PathVariable Long id) {
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/addOrEditOrRemove/{id}")
    public HttpEntity<?> addOrEditOrRemoveWorkspace(@PathVariable Long id,
                                                    @RequestBody MemberDto memberDto) {
        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveWorkspace(id, memberDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/join")
    public HttpEntity<?> joinToWorkspace(@RequestParam Long id,
                                         @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.joinToWorkspace(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/member/{id}")
    public HttpEntity<?> getMemberAndGuest(@PathVariable Long id) {
        List<MemberDto> members = workspaceService.getMemberAndGuest(id);
        return ResponseEntity.ok(members);
    }

    @GetMapping
    public HttpEntity<?> getMyWorkspaces(@CurrentUser User user) {
        List<WorkspaceDto> workspaces = workspaceService.getMyWorkspaces(user);
        return ResponseEntity.ok(workspaces);
    }

    @PutMapping("/addOrRemovePermission")
    public HttpEntity<?> addOrRemovePermissionToRole(@RequestBody WorkspaceRoleDto workspaceRoleDto) {
        ApiResponse apiResponse = workspaceService.addOrRemovePermissionToRole(workspaceRoleDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
