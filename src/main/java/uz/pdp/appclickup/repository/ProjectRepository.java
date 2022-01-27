package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickup.entity.Project;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByNameAndSpaceId(String name, Long space_id);
    boolean existsByNameAndSpaceIdAndIdNot(String name, Long space_id, Long id);
}
