package com.hello.jpabook_ch7_adavanced_mapping._02_single_table_strategy;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
	@Id
	@GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;

	private String name; // 이름
	private int price; // 가격
}
