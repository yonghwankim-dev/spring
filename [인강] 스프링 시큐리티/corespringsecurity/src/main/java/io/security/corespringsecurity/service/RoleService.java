package io.security.corespringsecurity.service;

import java.util.List;

import io.security.corespringsecurity.domain.entity.Role;

public interface RoleService {
	List<Role> getRoles();

	void createRole(Role role);

	Role getRole(Long id);

	void deleteRole(Long id);
}
