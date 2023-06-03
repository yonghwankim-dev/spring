package com.hello.jpabook_ch7_adavanced_mapping._06_combination_key_identity_relation_mapping.embeddedid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GrandChild {
    @EmbeddedId
    private GrandChildId id;

    @MapsId("childId") // GrandChildId.childId 매핑
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child child;

    private String name;
}
