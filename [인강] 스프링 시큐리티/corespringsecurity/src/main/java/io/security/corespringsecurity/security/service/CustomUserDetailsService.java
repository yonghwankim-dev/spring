package io.security.corespringsecurity.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.AccountRole;
import io.security.corespringsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = userRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> roles = account.getAccountRoleSet().stream()
			.map(AccountRole::getRoleName)
			.map(SimpleGrantedAuthority::new)
			.collect(Collectors.toList());
		return new AccountContext(account, roles);
	}
}
