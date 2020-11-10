package uz.appexpertserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.appexpertserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProjectChat extends AbsEntity {

    @ManyToOne
    private Project project;

    @OneToOne
    private Attachment attachment;
    private String request;
    private boolean responded;
    private String response;
    @OneToOne
    private Attachment byClient;
}
