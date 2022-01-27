package uz.pdp.appclickup.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.enums.TaskPermission;
import uz.pdp.appclickup.entity.template.AbsNameEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CategoryUser extends AbsNameEntity {
    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;




}
