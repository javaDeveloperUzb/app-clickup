package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.TaskHistory;

import java.util.UUID;

@RepositoryRestResource(path = "/taskHistory", collectionResourceRel = "list")
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, UUID> {
}
