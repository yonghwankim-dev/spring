package com.hello.jpabook_ch7_adavanced_mapping._06_combination_key_identity_relation_mapping.idclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Parent {
    @Id
    @Column(name = "PARENT_ID")
    private String id;
    private String name;
}
