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
public class Icon extends AbsNameEntity {
    @Column(nullable = false)
    private String name;

    @OneToOne
    private Attachment attachment;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String initialLetter;

    @Column(nullable = false)
    private String icon;

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    public static class CheckListItem extends AbsNameEntity {
        @Column(nullable = false)
        private String name;

        @ManyToOne
        private CheckList checkList;

        private boolean resolved;

        @ManyToOne
        private User assignedUser;
    }
}
