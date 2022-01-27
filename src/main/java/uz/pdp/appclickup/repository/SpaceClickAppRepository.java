package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.SpaceClickApp;

@RepositoryRestResource(path = "/spaceClickApp",collectionResourceRel = "list")
public interface SpaceClickAppRepository extends JpaRepository<SpaceClickApp, Long> {
}
