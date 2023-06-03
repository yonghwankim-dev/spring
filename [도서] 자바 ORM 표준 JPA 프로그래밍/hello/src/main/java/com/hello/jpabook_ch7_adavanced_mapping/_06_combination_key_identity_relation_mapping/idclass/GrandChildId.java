package com.hello.jpabook_ch7_adavanced_mapping._06_combination_key_identity_relation_mapping.idclass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"child", "id"})
public class GrandChildId implements Serializable {

    private ChildId child; // GrandChild.child 매핑
    private String id;      // GrandChild.id 매핑
}
