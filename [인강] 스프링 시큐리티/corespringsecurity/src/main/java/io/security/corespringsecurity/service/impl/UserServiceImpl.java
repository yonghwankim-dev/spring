package io.security.corespringsecurity.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.AccountRole;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.repository.UserRepository;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("userService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void createUser(Account account) {
		userRepository.save(account);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Account> getUsers() {
		return userRepository.findAll();
	}

	@Transactional
	@Override
	public void modifyUser(AccountDto accountDto) {
		ModelMapper modelMapper = new ModelMapper();
		Account account = modelMapper.map(accountDto, Account.class);

		if (accountDto.getRoles() != null) {
			Set<Role> roles = new HashSet<>();
			accountDto.getRoles().forEach(role -> {
				Role r = roleRepository.findByRoleName(role);
				roles.add(r);
			});
			Set<AccountRole> accountRoleSet = roles.stream()
				.map(r -> AccountRole.create(account, r))
				.collect(Collectors.toSet());
			account.setAccountRoleSet(accountRoleSet);
		}
		account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		userRepository.save(account);
	}

	@Transactional
	@Override
	public AccountDto getUser(Long id) {
		Account account = userRepository.findById(id).orElseGet(Account::empty);
		ModelMapper modelMapper = new ModelMapper();
		AccountDto accountDto = modelMapper.map(account, AccountDto.class);

		List<String> roles = account.getAccountRoleSet()
			.stream()
			.map(AccountRole::getRoleName)
			.toList();
		accountDto.setRoles(roles);
		return accountDto;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Secured("ROLE_MANAGER")
	public void order() {
		log.info("order");
	}
}
