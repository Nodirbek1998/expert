package uz.appexpertserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.appexpertserver.entity.ProjectChat;
import uz.appexpertserver.peyload.ApiResponse;
import uz.appexpertserver.peyload.ReqProjectChat;
import uz.appexpertserver.repository.ProjectChatRepository;
import uz.appexpertserver.service.ProjectChatService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projectChat")
public class ProjectChatController {

    @Autowired
    ProjectChatService projectChatService;
    @Autowired
    ProjectChatRepository projectChatRepository;
    @PostMapping
    public HttpEntity<?> addProjectChat(@RequestBody ReqProjectChat reqProjectChat){
        ApiResponse apiResponse = projectChatService.addProjectChat(reqProjectChat);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<ProjectChat> getProjectChat(@PathVariable UUID id){
        ProjectChat projectChat = projectChatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Malumot topilmadi"));
        return ResponseEntity.ok(projectChat);
    }
    @GetMapping
    public HttpEntity<?> getProjectChats(){
        List<ProjectChat> all = projectChatRepository.findAll();
        return ResponseEntity.ok(all);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editProjectChat(@PathVariable UUID id, @RequestBody ReqProjectChat reqProjectChat){
        ApiResponse apiResponse = projectChatService.editProjectChat(reqProjectChat, id);
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProjectChat(@PathVariable UUID id){
        projectChatRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true,"Malumot o`chirildi"));
    }
}
