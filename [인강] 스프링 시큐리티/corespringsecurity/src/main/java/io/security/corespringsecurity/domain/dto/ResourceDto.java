package io.security.corespringsecurity.domain.dto;

import java.util.HashSet;
import java.util.Set;

import io.security.corespringsecurity.domain.entity.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class ResourceDto {
	private String id;
	private String resourceName;
	private String httpMethod;
	private int orderNum;
	private String resourceType;
	private String roleName;
	private Set<Role> roleSet;

	public static ResourceDto empty() {
		ResourceDto dto = new ResourceDto();
		dto.roleSet = new HashSet<>();
		dto.roleSet.add(Role.empty());
		return dto;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
}
