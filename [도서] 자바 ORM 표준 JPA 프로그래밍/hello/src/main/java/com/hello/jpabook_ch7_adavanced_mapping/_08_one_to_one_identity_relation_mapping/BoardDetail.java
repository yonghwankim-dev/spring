package com.hello.jpabook_ch7_adavanced_mapping._08_one_to_one_identity_relation_mapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class BoardDetail {
    @Id
    private Long boardId;

    @MapsId // BoardDetail.boardId 매핑
    @OneToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    private String content;
}
