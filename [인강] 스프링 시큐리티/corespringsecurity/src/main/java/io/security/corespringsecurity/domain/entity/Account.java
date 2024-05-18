package io.security.corespringsecurity.domain.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString(exclude = "accountRoleSet")
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String username;

	@Column
	private String email;

	@Column
	private int age;

	@Column
	private String password;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<AccountRole> accountRoleSet = new HashSet<>();

	public static Account createAccount(String userName, String email, String password) {
		return new Account(
			null,
			userName,
			email,
			0,
			password,
			new HashSet<>()
		);
	}

	public void addAccountRole(AccountRole accountRole){
		this.accountRoleSet.add(accountRole);
	}

	public static Account empty(){
		return new Account();
	}
}
