package io.security.corespringsecurity.domain.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class AccountDto {
	private String id;
	private String username;
	private String email;
	private int age;
	private String password;
	private String role;
	private List<String> roles;
}
