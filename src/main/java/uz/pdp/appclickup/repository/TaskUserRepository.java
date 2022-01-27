package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.TaskUser;

import java.util.UUID;

@RepositoryRestResource(path = "/taskUser", collectionResourceRel = "list")
public interface TaskUserRepository extends JpaRepository<TaskUser, UUID> {
}
