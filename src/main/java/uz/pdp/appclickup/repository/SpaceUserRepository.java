package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.SpaceUser;

import java.util.UUID;

@RepositoryRestResource(path = "/spaceUser", collectionResourceRel = "list")
public interface SpaceUserRepository extends JpaRepository<SpaceUser, UUID> {
//    SpaceUser findBySpaceIdAndMemberId(Long space_id, UUID member_id);
    boolean existsBySpaceIdAndMemberId(Long space_id, UUID member_id);
}
