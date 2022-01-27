package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.View;

@RepositoryRestResource(path = "/view",collectionResourceRel = "list")
public interface ViewRepository extends JpaRepository<View, Long> {
}
