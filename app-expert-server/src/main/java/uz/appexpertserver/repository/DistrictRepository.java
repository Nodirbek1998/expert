package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sun.java2d.loops.CustomComponent;
import uz.appexpertserver.entity.District;

@RepositoryRestResource(path = "district")
public interface DistrictRepository extends JpaRepository<District,Integer> {
}
