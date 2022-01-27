package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.Tag;

@RepositoryRestResource(path = "/tag", collectionResourceRel = "list")
public interface TagRepository extends JpaRepository<Tag, Long> {
}
