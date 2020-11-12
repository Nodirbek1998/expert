package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.appexpertserver.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
