package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickup.entity.CategoryUser;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryUserRepository extends JpaRepository<CategoryUser, Long> {
    List<CategoryUser> findAllByUserId(UUID user_id);
}
