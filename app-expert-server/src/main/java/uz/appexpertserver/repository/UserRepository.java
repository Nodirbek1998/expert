package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.appexpertserver.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByPhoneNumberEqualsIgnoreCase(String phoneNumber);
    boolean existsByEmailEqualsIgnoreCase(String email);
    boolean existsByTin(String tin);
}
