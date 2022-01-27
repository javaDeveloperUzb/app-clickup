package uz.pdp.appclickup.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project extends AbsNameEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Space space;

    @Column(nullable = false)
    private String accessType;

    @Column(nullable = false)
    private boolean archived;

    @Column(nullable = false)
    private String color;
}
