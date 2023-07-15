package com.hello.jpabook_ch6_relationship_mapping.one_to_one.main_table.twoway;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Locker {

	@Id
	@GeneratedValue
	@Column(name = "LOCKER_ID")
	private Long id;

	private String name;

	@OneToOne(mappedBy = "locker")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		if (member.getLocker() != this) {
			member.setLocker(this);
		}
	}
}
