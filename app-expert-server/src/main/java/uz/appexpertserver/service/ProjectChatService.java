package uz.appexpertserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.appexpertserver.entity.Project;
import uz.appexpertserver.entity.ProjectChat;
import uz.appexpertserver.peyload.ApiResponse;
import uz.appexpertserver.peyload.ReqProjectChat;
import uz.appexpertserver.repository.AttachmentRepository;
import uz.appexpertserver.repository.ProjectChatRepository;
import uz.appexpertserver.repository.ProjectRepository;

import java.util.UUID;

@Service
public class ProjectChatService {


    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectChatRepository projectChatRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    public ApiResponse addProjectChat(ReqProjectChat reqProjectChat){
        Project project = projectRepository.findById(reqProjectChat.getProject()).orElseThrow(() -> new ResourceNotFoundException("Bunday loyiha mavjud emas"));
        ProjectChat projectChat = new ProjectChat();
        projectChat.setProject(project);
        projectChat.setAttachment(attachmentRepository.findById(reqProjectChat.getAttachment()).orElse(null));
        projectChat.setByClient(attachmentRepository.findById(reqProjectChat.getByClient()).orElse(null));
        projectChat.setRequest(reqProjectChat.getRequest());
        projectChat.setResponse(reqProjectChat.getResponse());
        try {
            projectChatRepository.save(projectChat);
        }catch (Exception e){
            throw new ResourceNotFoundException("Malumot saqlanmadi");
        }
        return new ApiResponse(true,"Malumot saqlandi");
    }
    public ApiResponse editProjectChat(ReqProjectChat reqProjectChat, UUID id){
        ProjectChat projectChat = projectChatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bunday chat mavjud emas"));
        Project project = projectRepository.findById(reqProjectChat.getProject()).orElseThrow(() -> new ResourceNotFoundException("Bunday loyiha mavjud emas"));
        projectChat.setProject(project);
        projectChat.setAttachment(attachmentRepository.findById(reqProjectChat.getAttachment()).orElse(null));
        projectChat.setByClient(attachmentRepository.findById(reqProjectChat.getByClient()).orElse(null));
        projectChat.setRequest(reqProjectChat.getRequest());
        projectChat.setResponse(reqProjectChat.getResponse());
        try {
            projectChatRepository.save(projectChat);
        }catch (Exception e){
            throw new ResourceNotFoundException("Malumot o`zgartirilmadi");
        }
        return new ApiResponse(true,"Malumot o`zgartirildi");
    }
}
