package io.security.corespringsecurity.controller.admin;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.security.corespringsecurity.domain.dto.RoleDto;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RoleController {
	private final RoleService roleService;

	@GetMapping(value = "/admin/roles")
	public String getRoles(Model model) {
		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles", roles);
		return "admin/role/list";
	}

	@GetMapping(value = "/admin/roles/register")
	public String viewRoles(Model model) {
		RoleDto roleDto = RoleDto.empty();
		model.addAttribute("role", roleDto);
		return "admin/role/detail";
	}

	@PostMapping(value = "/admin/roles")
	public String createRole(RoleDto roleDto){
		ModelMapper modelMapper = new ModelMapper();
		Role role = modelMapper.map(roleDto, Role.class);
		roleService.createRole(role);
		return "redirect:/admin/roles";
	}

	@GetMapping(value = "/admin/roles/{id}")
	public String getRole(@PathVariable Long id, Model model){
		Role role = roleService.getRole(id);

		ModelMapper modelMapper = new ModelMapper();
		RoleDto roleDto = modelMapper.map(role, RoleDto.class);
		model.addAttribute("role", roleDto);

		return "admin/role/detail";
	}

	@GetMapping(value = "/admin/roles/delete/{id}")
	public String removeRole(@PathVariable Long id, Model model) {
		Role role = roleService.getRole(id);
		roleService.deleteRole(id);
		return "redirect:/admin/resources";
	}
}
