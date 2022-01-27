package uz.pdp.appclickup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.appclickup.entity.SpaceView;

import java.util.List;

@RepositoryRestResource(path = "/spaceView",collectionResourceRel = "list")
public interface SpaceViewRepository extends JpaRepository<SpaceView, Long> {
    List<SpaceView> findAllBySpaceId(Long space_id);
}
