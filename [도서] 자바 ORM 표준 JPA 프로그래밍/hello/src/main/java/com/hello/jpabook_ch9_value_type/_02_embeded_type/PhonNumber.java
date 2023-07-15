package com.hello.jpabook_ch9_value_type._02_embeded_type;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class PhonNumber {

	private String areaCode;
	private String localNumber;
	@ManyToOne
	private PhoneServiceProvider provider;
}
