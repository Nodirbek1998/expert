package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.appexpertserver.entity.District;
import uz.appexpertserver.entity.Region;

@RepositoryRestResource(path = "region")
public interface RegionRepository extends JpaRepository<Region,Integer> {
}
