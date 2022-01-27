package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickup.entity.WorkspaceRole;
import uz.pdp.appclickup.entity.enums.WorkspaceRoleName;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceRoleRepository extends JpaRepository<WorkspaceRole, UUID> {
    boolean existsByWorkspaceIdAndName(Long workspace_id, String name);
//    Optional<WorkspaceRole> findByExtendsRole(WorkspaceRoleName extendsRole);
}
