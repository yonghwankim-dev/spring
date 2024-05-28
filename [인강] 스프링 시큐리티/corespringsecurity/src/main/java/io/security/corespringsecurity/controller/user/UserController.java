package io.security.corespringsecurity.controller.user;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.AccountRole;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@GetMapping("/mypage")
	public String myPage() {
		return "user/mypage";
	}

	@GetMapping("/order")
	public String order() {
		userService.order();
		return "user/mypage";
	}

	@GetMapping("/users")
	public String createUser() {
		return "user/login/register";
	}

	@PostMapping("/users")
	public String createUser(AccountDto accountDto) {
		ModelMapper modelMapper = new ModelMapper();
		Account account = modelMapper.map(accountDto, Account.class);
		account.setPassword(passwordEncoder.encode(account.getPassword()));

		Role role = roleRepository.findByRoleName(accountDto.getRole());
		AccountRole accountRole = AccountRole.create(account, role);
		account.addAccountRole(accountRole);

		userService.createUser(account);
		log.info("회원 가입 완료 : {}", account);
		return "redirect:/";
	}
}
