package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.appexpertserver.entity.Attachment;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
   Optional<List<Attachment>> findAllByIdIn(Collection<UUID> id);
}
