package uz.appexpertserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.appexpertserver.entity.Project;
import uz.appexpertserver.peyload.ApiResponse;
import uz.appexpertserver.peyload.ReqProject;
import uz.appexpertserver.repository.ProjectRepository;
import uz.appexpertserver.service.ProjectService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectRepository projectRepository;
    @PostMapping
    public HttpEntity<?> addProject(@RequestBody ReqProject reqProject){
        ApiResponse apiResponse = projectService.addProject(reqProject);
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
    public HttpEntity<?> getProjects(){
        List<Project> all = projectRepository.findAll();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getProject(@PathVariable UUID id){
        Project project = projectService.getProject(id);
        return ResponseEntity.ok(project);
    }
    @PutMapping("/{id}")
    public  HttpEntity<?> editProject(@RequestBody ReqProject reqProject, @PathVariable UUID id){
        ApiResponse apiResponse = projectService.editProject(reqProject, id);
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProject(@PathVariable UUID id){
        projectRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Project o`chirildi"));
    }

}
