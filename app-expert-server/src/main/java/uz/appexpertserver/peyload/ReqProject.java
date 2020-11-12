package uz.appexpertserver.peyload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.appexpertserver.entity.enums.ExpertizeType;
import uz.appexpertserver.entity.enums.PersonType;
import uz.appexpertserver.entity.enums.ProjectStatus;
import uz.appexpertserver.entity.enums.ProjectType;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqProject {
    private UUID user;
    private String name;
    private String projector;
    private String projectorTin;
    private String projectorPhoneNumber;
    private UUID expert;
    private double price;
    private PersonType personType;
    private ProjectType projectType;
    private ExpertizeType expertizeType;
    private Integer appNumber;
    private ProjectStatus projectStatus;
    private List<UUID> permissionOrganization;

    private List<UUID> engineeringAndSearching;

    private List<UUID> art;

    private List<UUID> confirmedDraft;

    private List<UUID> working;

    private List<UUID> defectAct;

    private List<UUID> taskProject;


}
