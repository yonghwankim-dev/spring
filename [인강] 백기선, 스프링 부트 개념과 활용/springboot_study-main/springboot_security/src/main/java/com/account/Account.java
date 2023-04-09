package com.account;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;

}
