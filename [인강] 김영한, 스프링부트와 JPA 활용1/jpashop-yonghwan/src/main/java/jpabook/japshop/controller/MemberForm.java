package jpabook.japshop.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름을 입력해주세요")
    private String name;
    private String city;
    private String street;
    private String zipcode;

}
