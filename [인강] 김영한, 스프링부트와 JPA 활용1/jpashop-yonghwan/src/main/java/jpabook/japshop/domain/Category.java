package jpabook.japshop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private final List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private final List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }

    public void setParent(Category parent){
        this.parent = parent;
    }
}
