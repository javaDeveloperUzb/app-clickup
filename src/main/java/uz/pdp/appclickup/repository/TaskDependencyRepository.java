package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickup.entity.TaskDependency;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

}
