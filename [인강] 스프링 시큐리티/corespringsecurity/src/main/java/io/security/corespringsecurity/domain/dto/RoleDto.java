package io.security.corespringsecurity.domain.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RoleDto {
	private String roleName;
	private String roleDesc;
	public static RoleDto empty(){
		return new RoleDto();
	}
}
