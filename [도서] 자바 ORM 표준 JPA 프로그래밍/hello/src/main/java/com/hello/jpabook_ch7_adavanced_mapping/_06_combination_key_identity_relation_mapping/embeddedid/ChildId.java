package com.hello.jpabook_ch7_adavanced_mapping._06_combination_key_identity_relation_mapping.embeddedid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode(of = {"parentId", "id"})
@NoArgsConstructor
@AllArgsConstructor
public class ChildId implements Serializable {
    private String parentId; // @MpasId("parentId")로 매핑

    @Column(name = "CHILD_ID")
    private String id;
}
