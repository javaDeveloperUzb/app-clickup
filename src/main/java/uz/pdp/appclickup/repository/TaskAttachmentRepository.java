package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.TaskAttachment;

@RepositoryRestResource(path = "/taskAttachment", collectionResourceRel = "list")
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {
}
