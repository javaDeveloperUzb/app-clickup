package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appclickup.entity.User;
import uz.pdp.appclickup.entity.WorkspaceUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {
    Optional<WorkspaceUser> findByWorkspaceIdAndUserId(Long workspace_id, UUID user_id);

    @Transactional
    @Modifying
    void deleteByWorkspaceIdAndUserId(Long workspace_id, UUID user_id);

    List<WorkspaceUser> findAllByWorkspaceId(Long workspaceId);

    List<WorkspaceUser> findAllByUserId(UUID user_id);

    WorkspaceUser findByUserId(UUID id);
}