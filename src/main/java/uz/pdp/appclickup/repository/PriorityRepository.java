package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.Priority;

@RepositoryRestResource(path = "/priority", collectionResourceRel = "list")
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
