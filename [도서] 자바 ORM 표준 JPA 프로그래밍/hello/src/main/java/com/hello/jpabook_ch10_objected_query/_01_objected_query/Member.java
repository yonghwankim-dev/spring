package com.hello.jpabook_ch10_objected_query._01_objected_query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String username;

    private int age;
}
