package uz.appexpertserver.entity;

import com.sun.applet2.preloader.event.InitEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.appexpertserver.entity.enums.ExpertizeType;
import uz.appexpertserver.entity.enums.PersonType;
import uz.appexpertserver.entity.enums.ProjectStatus;
import uz.appexpertserver.entity.enums.ProjectType;
import uz.appexpertserver.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Project extends AbsEntity {

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    @ManyToOne
    private User user;

    private String name;
    private String projector;
    private String projectorTin;
    private String projectorPhoneNumber;

    private boolean seenAdmin;
    private boolean seenExpert;
    private boolean seenClient = true;
    @ManyToOne
    private User expert;

    private double price;

    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @Enumerated(EnumType.STRING)
    private ExpertizeType expertizeType;

    @OneToMany(mappedBy = "project")
    private List<ProjectChat> projectChats;

    @Column(updatable = false)
    private Integer appNumber;

    @Enumerated(EnumType.STRING)
    private ProjectStatus projectStatus;


    @OneToMany
    private List<Attachment> permissionOrganization;
    @OneToMany
    private List<Attachment> engineeringAndSearching;
    @OneToMany
    private List<Attachment> art;
    @OneToMany
    private List<Attachment> confirmedDraft;
    @OneToMany
    private List<Attachment> working;
    @OneToMany
    private List<Attachment> defectAct;
    @OneToMany
    private List<Attachment> taskProject;


}
