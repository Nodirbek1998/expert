package uz.appexpertserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.appexpertserver.entity.Attachment;
import uz.appexpertserver.entity.AttachmentContent;
import uz.appexpertserver.repository.AttachmentContentRepository;
import uz.appexpertserver.repository.AttachmentRepository;

import java.io.IOException;
import java.util.*;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    public List<UUID> uploadFiles(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        List<UUID> uuidList = new ArrayList<>();
        while (fileNames.hasNext()){
            MultipartFile file = request.getFile(fileNames.next());
            assert file != null;
            Attachment attachment = new Attachment(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize()
            );
            Attachment savedAttachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(),attachment);
            attachmentContentRepository.save(attachmentContent);
            uuidList.add(attachment.getId());
        }
        return uuidList;
    }

    public HttpEntity<?> getFile(UUID id){
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            AttachmentContent attachmentContent = attachmentContentRepository.findByAttachment(attachment);
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(attachment.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment\""+attachment.getName()+"\"")
                    .body(attachmentContent.getContent());
        }
        return null;
    }
}
