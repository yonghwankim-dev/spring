package com.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    @Column(name = "userid", unique = true, nullable = false)
    private String userid;
    private String password;

    @Builder
    public Member(String name, String userid, String password) {
        this.name = name;
        this.userid = userid;
        this.password = password;
    }

    public boolean equalUseridAndPassword(String userid, String password){
        return equalUserid(userid) && equalPassword(password);
    }

    private boolean equalUserid(String userid){
        return this.userid.equals(userid);
    }

    private boolean equalPassword(String password){
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)",name, userid);
    }
}
