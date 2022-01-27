package uz.pdp.appclickup.service;

import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.MemberDto;
import uz.pdp.appclickup.payload.WorkspaceDto;
import uz.pdp.appclickup.payload.WorkspaceRoleDto;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user);

    ApiResponse  editWorkspace(WorkspaceDto workspaceDto);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId, User user);

    ApiResponse deleteWorkspace(Long id);

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDto memberDto);

    ApiResponse joinToWorkspace(Long id, User user);

    List<MemberDto> getMemberAndGuest(Long id);

    List<WorkspaceDto> getMyWorkspaces(User user);

    ApiResponse addOrRemovePermissionToRole(WorkspaceRoleDto workspaceRoleDto);

    ApiResponse addRole(Long workspaceId, WorkspaceRoleDto workspaceRoleDto, User user);
}
