package uz.pdp.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.enums.StatusType;
import uz.pdp.appclickup.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Status extends AbsNameEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Space space;

    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne(optional = false)
    private Category category;

    @Column(nullable = false)
    private String color;

    private StatusType type;


}
