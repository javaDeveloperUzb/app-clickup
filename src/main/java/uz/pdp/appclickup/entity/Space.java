package uz.pdp.appclickup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appclickup.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Space extends AbsNameEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne(optional = false)
    private Workspace workspace;

    @Column(nullable = false)
    private String initialLetter;

    @ManyToOne
    private Icon icon;

    @OneToOne
    private Attachment avatar;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private String accessType;


    public Space(String name, String color, Workspace workspace, Icon icon, Attachment avatar, User owner, String accessType) {
        this.name = name;
        this.color = color;
        this.workspace = workspace;
        this.icon = icon;
        this.avatar = avatar;
        this.owner = owner;
        this.accessType = accessType;
    }

    @PrePersist
    @PreUpdate
    public void setInitialLetterMyMethod() {
        this.initialLetter = name.substring(0, 1);
    }




}
