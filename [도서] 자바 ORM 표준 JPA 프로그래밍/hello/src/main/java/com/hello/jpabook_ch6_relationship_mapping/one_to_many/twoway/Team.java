package com.hello.jpabook_ch6_relationship_mapping.one_to_many.twoway;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "members")
@Entity
public class Team {

	@Id
	@GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	private String name;

	@OneToMany
	@JoinColumn(name = "TEAM_ID") // Member 테이블의 TEAM_ID (FK)
	private List<Member> members = new ArrayList<>();
}
