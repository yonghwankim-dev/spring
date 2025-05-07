package com.nemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveRequest {
	private Long id;
	private String name;
	private int age;

	public Member toEntity() {
		return new Member(id, name, age);
	}
}
