package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.Comment;

@RepositoryRestResource(path = "/comment", collectionResourceRel = "list")
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
