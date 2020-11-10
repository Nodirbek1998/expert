package uz.appexpertserver.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.appexpertserver.entity.template.AbsNameEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Region extends AbsNameEntity {


}
