package com.hello.jpabook_ch7_adavanced_mapping._06_combination_key_identity_relation_mapping.embeddedid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class GrandChildId implements Serializable {

    private ChildId childId; // @MapsId("childId")로 매핑

    @Column(name = "GRANDCHILD_ID")
    private String id;
}
