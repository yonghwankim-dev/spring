package com.hello.jpabook_practice.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.print.attribute.standard.MediaSize.NA;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@TableGenerator(
    name = "ITEM_SEQ_GENERATOR",
    table = "JPABOOK_SEQUENCES",
    pkColumnValue = "ITEM_SEQ"
)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ITEM_SEQ_GENERATOR")
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;       // 이름
    private int price;         // 가격
    private int stockQuantity; // 재고수량

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public void addCategoryItem(CategoryItem categoryItem) {
        categoryItems.add(categoryItem);
    }
}
