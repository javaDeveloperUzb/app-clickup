package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appclickup.entity.Space;

import java.util.UUID;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {
    boolean existsByNameAndOwnerId(String name, UUID owner_id);
}
