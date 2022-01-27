package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.TaskTag;

@RepositoryRestResource(path = "/taskTag", collectionResourceRel = "list")
public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
