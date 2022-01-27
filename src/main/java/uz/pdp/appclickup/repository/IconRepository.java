package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.Icon;

import java.util.UUID;

@RepositoryRestResource(path = "/icon", collectionResourceRel = "list")
public interface IconRepository extends JpaRepository<Icon, Long> {
}
