package io.security.corespringsecurity.security.listener;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.AccountRole;
import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.domain.entity.ResourcesRole;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.domain.entity.RoleHierarchy;
import io.security.corespringsecurity.repository.ResourcesRepository;
import io.security.corespringsecurity.repository.RoleHierarchyRepository;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;
	private final RoleHierarchyRepository roleHierarchyRepository;
	private final RoleRepository roleRepository;
	private final ResourcesRepository resourcesRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	private static AtomicInteger count = new AtomicInteger(0);

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		setupSecurityResources();

		alreadySetup = true;
	}

	@Transactional
	public void setupSecurityResources() {
		Set<Role> roles = new HashSet<>();
		Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
		roles.add(adminRole);
		createResourceIfNotFound("/admin/**", "", roles, "url");
		createUserIfNotFound("admin@gmail.com", "admin@admin.com", "pass", roles);
		Role managerRole = createRoleIfNotFound("ROLE_MANAGER", "매니저");
		roles.add(managerRole);
		Role userRole = createRoleIfNotFound("ROLE_USER", "유저");
		createResourceIfNotFound("/mypage", "", Set.of(userRole), "url");


		createRoleHierarchyIfNotFound(managerRole, adminRole);
		createRoleHierarchyIfNotFound(userRole, managerRole);
	}

	@Transactional
	public Role createRoleIfNotFound(String roleName, String roleDesc) {
		Role role = roleRepository.findByRoleName(roleName);
		if (role == null) {
			role = Role.createRole(roleName, roleDesc);
		}
		return roleRepository.save(role);
	}

	@Transactional
	public Resources createResourceIfNotFound(String resourceName, String httpMethod, Set<Role> roleSet, String resourceType) {
		Resources resources = resourcesRepository.findByResourceNameAndHttpMethod(resourceName,
			httpMethod);

		if (resources == null) {
			resources = Resources.createResources(resourceName, httpMethod, count.incrementAndGet(), resourceType);
			Resources finalResources = resources;
			Set<ResourcesRole> resourcesRoleSet = roleSet.stream()
				.map(r -> ResourcesRole.create(finalResources, r))
				.collect(Collectors.toSet());
			resources.setResourcesRoleSet(resourcesRoleSet);
		}
		return resourcesRepository.save(resources);
	}

	@Transactional
	public void createUserIfNotFound(String userName, String email, String password, Set<Role> roleSet) {
		Account account = userRepository.findByUsername(userName);

		if (account == null) {
			account = Account.createAccount(userName, email, passwordEncoder.encode(password));
			Account finalAccount = account;
			Set<AccountRole> accountRoleSet = roleSet.stream()
				.map(r -> AccountRole.create(finalAccount, r))
				.collect(Collectors.toSet());
			account.setAccountRoleSet(accountRoleSet);
		}
		userRepository.save(account);
	}

	@Transactional
	public void createRoleHierarchyIfNotFound(Role childRole, Role parentRole){
		RoleHierarchy roleHierarchy = roleHierarchyRepository.findByChildName(parentRole.getRoleName());
		if (roleHierarchy == null){
			roleHierarchy = RoleHierarchy.createRoleHierarchy(parentRole.getRoleName());
		}
		RoleHierarchy parentRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

		roleHierarchy = roleHierarchyRepository.findByChildName(childRole.getRoleName());
		if (roleHierarchy == null){
			roleHierarchy = RoleHierarchy.createRoleHierarchy(childRole.getRoleName());
		}

		RoleHierarchy childRoleHierarchy = roleHierarchyRepository.save(roleHierarchy);

		childRoleHierarchy.setParentName(parentRoleHierarchy);
	}
}
