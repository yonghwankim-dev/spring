package com.hello.jpabook_practice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
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
}
