package com.hello.jpabook_ch7_adavanced_mapping._08_one_to_one_identity_relation_mapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long id;
    private String title;

    @OneToOne(mappedBy = "board")
    private BoardDetail boardDetail;
}
