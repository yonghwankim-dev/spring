package io.security.corespringsecurity.security.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

import io.security.corespringsecurity.service.RoleHierarchyService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {

	private final RoleHierarchyService roleHierarchyService;

	private final RoleHierarchyImpl roleHierarchyImpl;

	@Override
	public void run(ApplicationArguments args) {
		String allHierarchy = roleHierarchyService.findAllHierarchy();
		roleHierarchyImpl.setHierarchy(allHierarchy);
	}
}
