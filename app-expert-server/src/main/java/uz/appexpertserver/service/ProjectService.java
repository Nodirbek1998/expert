package uz.appexpertserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.appexpertserver.entity.Attachment;
import uz.appexpertserver.entity.Project;
import uz.appexpertserver.entity.User;
import uz.appexpertserver.entity.enums.ProjectType;
import uz.appexpertserver.peyload.ApiResponse;
import uz.appexpertserver.peyload.ReqProject;
import uz.appexpertserver.repository.AttachmentRepository;
import uz.appexpertserver.repository.ProjectRepository;
import uz.appexpertserver.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    public ApiResponse addProject(ReqProject reqProject) {
        Project project = new Project();
        saveProject(reqProject, project);
        return new ApiResponse(true,"Mavaffiyatli amalga oshirildi");
    }
    public Project getProject(UUID id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bunday project topilmadi"));
        return project;
    }
    public ApiResponse editProject(ReqProject reqProject, UUID id){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bunday project topilmadi."));
        saveProject(reqProject,project);
        return new ApiResponse(true,"Project o`zgartirildi");
    }

    public void saveProject(ReqProject reqProject, Project project){
        User user = userRepository.findById(reqProject.getUser()).orElseThrow(() -> new ResourceNotFoundException("Foydalanuvchi topilmadi"));
        project.setUser(user);
        try {
           if (reqProject.getProjectType() == ProjectType.RECONSTRUCTION){
               List<Attachment> attachments = attachmentRepository.findAllByIdIn(reqProject.getPermissionOrganization()).orElseThrow(() -> new ResourceNotFoundException("Ruhsat etuvchi tashkilotlar ruhsat va hulosalari topilmadi."));
               project.setPermissionOrganization(attachments);
               List<Attachment> engineeringAndSearching = attachmentRepository.findAllByIdIn(reqProject.getEngineeringAndSearching()).orElseThrow(() -> new ResourceNotFoundException("Muhandislik izlanishlari (geologiya va topoxarita) topilmadi."));
               project.setEngineeringAndSearching(engineeringAndSearching);
               List<Attachment> art = attachmentRepository.findAllByIdIn(reqProject.getArt()).orElseThrow(() -> new ResourceNotFoundException("ART va loyihalash topshirig`i topilmadi"));
               project.setArt(art);
               List<Attachment> confirmedDraft = attachmentRepository.findAllByIdIn(reqProject.getConfirmedDraft()).orElseThrow(() -> new ResourceNotFoundException("Tasdiqlangan eskiz loyiha topilmadi"));
               project.setConfirmedDraft(confirmedDraft);
           }
           if (reqProject.getProjectType() == ProjectType.CURRENT_REPAIRING){
               List<Attachment> defectAct = attachmentRepository.findAllByIdIn(reqProject.getDefectAct()).orElseThrow(() -> new ResourceNotFoundException("Nuqsonlar dalolatnomi topilmadi"));
               project.setDefectAct(defectAct);
               List<Attachment> taskProject = attachmentRepository.findAllByIdIn(reqProject.getTaskProject()).orElseThrow(() -> new ResourceNotFoundException("Loyihalash topshirig`i topilmadi"));
                project.setTaskProject(taskProject);
           }
        }catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }

        List<Attachment> working = attachmentRepository.findAllByIdIn(reqProject.getWorking())
                .orElseThrow(() -> new ResourceNotFoundException("Ishchi loyiha topiladi."));
        project.setWorking(working);
        project.setPersonType(reqProject.getPersonType());
        project.setName(reqProject.getName());
        project.setName(reqProject.getName());
        project.setProjector(reqProject.getProjector());
        project.setProjectorTin(reqProject.getProjectorTin());
        project.setProjectorPhoneNumber(reqProject.getProjectorPhoneNumber());
        project.setProjectType(reqProject.getProjectType());
        project.setExpertizeType(reqProject.getExpertizeType());
        project.setAppNumber((int) new Date().getTime());
        project.setProjectStatus(reqProject.getProjectStatus());
        try {
            projectRepository.save(project);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Ma'lumot maslanmadi qaytadan urinib ko`ring.");
        }
    }
}
