package uz.pdp.appclickup.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.template.AbsNameEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbsNameEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Project project;

    @Column(nullable = false)
    private String accessType;

    @Column(nullable = false)
    private boolean archived;


    @Column(nullable = false)
    private String color;

}
