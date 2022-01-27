package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickup.entity.WorkspacePermission;
import uz.pdp.appclickup.entity.enums.WorkspacePermissionName;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspacePermissionRepository extends JpaRepository<WorkspacePermission, UUID> {
    Optional<WorkspacePermission> findByWorkspaceRoleIdAndPermission(UUID workspaceRole_id, WorkspacePermissionName permission);

    List<WorkspacePermission> findAllByWorkspaceRole_NameAndWorkspaceRole_WorkspaceId(String workspaceRole_name, Long workspaceRole_workspace_id);
    List<WorkspacePermission> findAllByWorkspaceRole_ExtendsRole(WorkspaceRoleName workspaceRole_extendsRole);
}
