package io.security.corespringsecurity.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Transactional
	@Override
	public void createRole(Role role) {
		roleRepository.save(role);
	}

	@Transactional(readOnly = true)
	@Override
	public Role getRole(Long id) {
		return roleRepository.findById(id).orElse(Role.empty());
	}

	@Transactional
	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}
}
