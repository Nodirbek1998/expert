package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.appexpertserver.entity.Role;
import uz.appexpertserver.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
