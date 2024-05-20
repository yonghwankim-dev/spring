package io.security.corespringsecurity.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.security.corespringsecurity.domain.entity.RoleHierarchy;
import io.security.corespringsecurity.repository.RoleHierarchyRepository;
import io.security.corespringsecurity.service.RoleHierarchyService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

	private final RoleHierarchyRepository roleHierarchyRepository;

	@Override
	@Transactional(readOnly = true)
	public String findAllHierarchy() {
		List<RoleHierarchy> rolesHierarchy = roleHierarchyRepository.findAll();

		Iterator<RoleHierarchy> itr = rolesHierarchy.iterator();
		StringBuilder concatRoles = new StringBuilder();
		while (itr.hasNext()) {
			RoleHierarchy roleHierarchy = itr.next();
			if (roleHierarchy.getParentName() != null) {
				concatRoles.append(roleHierarchy.getParentName().getChildName());
				concatRoles.append(" > ");
				concatRoles.append(roleHierarchy.getChildName());
				concatRoles.append("\n");
			}
		}
		return concatRoles.toString();
	}
}
