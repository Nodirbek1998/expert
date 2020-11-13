package uz.appexpertserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.appexpertserver.service.AttachmentService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/attach")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException {
        return ResponseEntity.ok(attachmentService.uploadFiles(request));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> downloadFile(@PathVariable UUID id){
        return attachmentService.getFile(id);
    }
}
