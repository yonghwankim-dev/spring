package io.security.corespringsecurity.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.service.RoleService;
import io.security.corespringsecurity.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserManagerController {

	private final UserService userService;
	private final RoleService roleService;

	@GetMapping(value="/admin/accounts")
	public String getUsers(Model model){
		List<Account> acounts = userService.getUsers();
		model.addAttribute("accounts", acounts);
		return "admin/user/list";
	}

	@PostMapping(value = "/admin/accounts")
	public String modifyUser(AccountDto accountDto){
		userService.modifyUser(accountDto);
		return "redirect:/admin/accounts";
	}


	@GetMapping(value = "/admin/accounts/{id}")
	public String getUser(@PathVariable Long id, Model model){
		AccountDto accountDto = userService.getUser(id);
		List<Role> roleList = roleService.getRoles();

		model.addAttribute("account", accountDto);
		model.addAttribute("roleList", roleList);
		return "admin/user/detail";
	}

	@GetMapping(value = "/admin/accounts/delete/{id}")
	public String removeUser(@PathVariable Long id, Model model){
		userService.deleteUser(id);
		return "redirect:/admin/users";
	}
}
