package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickup.entity.ProjectUser;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {
    boolean existsByProjectIdAndUserIdAndIdNot(Long project_id, UUID user_id, Long id);
    Optional<ProjectUser> findByProjectIdAndUserId(Long project_id, UUID user_id);
}
