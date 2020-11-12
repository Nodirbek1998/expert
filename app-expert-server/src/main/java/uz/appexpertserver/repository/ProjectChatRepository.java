package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.appexpertserver.entity.ProjectChat;

import java.util.UUID;

public interface ProjectChatRepository extends JpaRepository<ProjectChat, UUID> {
}
