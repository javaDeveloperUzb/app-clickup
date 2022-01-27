package uz.pdp.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.enums.DependencyType;
import uz.pdp.appclickup.entity.template.AbsNameEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskDependency extends AbsNameEntity {
    @ManyToOne
    private Task task;

    @ManyToOne
    private Task dependencyTask;

    @Enumerated(EnumType.STRING)
    private DependencyType dependencyType;
}
