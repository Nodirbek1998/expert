package uz.appexpertserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.appexpertserver.entity.Attachment;
import uz.appexpertserver.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,UUID> {
    AttachmentContent findByAttachment(Attachment attachment);
}
