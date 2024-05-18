package io.security.corespringsecurity.controller.admin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.security.corespringsecurity.domain.dto.ResourceDto;
import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.domain.entity.ResourcesRole;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.security.authorization_manager.UrlAuthorizationManager;
import io.security.corespringsecurity.service.ResourcesService;
import io.security.corespringsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResourceController {

	private final ResourcesService resourcesService;
	private final RoleRepository roleRepository;
	private final RoleService roleService;
	private final UrlAuthorizationManager urlAuthorizationManager;

	@GetMapping("/admin/resources")
	public String getResources(Model model) {
		List<Resources> resources = resourcesService.getResources();
		model.addAttribute("resources", resources);
		return "admin/resource/list";
	}

	@PostMapping("/admin/resources")
	public String createResources(ResourceDto resourceDto) {
		ModelMapper modelMapper = new ModelMapper();

		Role role = roleRepository.findByRoleName(resourceDto.getRoleName());
		Resources resources = modelMapper.map(resourceDto, Resources.class);
		Set<ResourcesRole> resourcesRoleSet = new HashSet<>();
		resourcesRoleSet.add(ResourcesRole.create(resources, role));
		resources.setResourcesRoleSet(resourcesRoleSet);

		resourcesService.createResources(resources);
		urlAuthorizationManager.reload();

		return "redirect:/admin/resources";
	}

	@GetMapping("/admin/resources/register")
	public String viewRoles(Model model) {
		List<Role> roleList = roleRepository.findAll();
		model.addAttribute("roleList", roleList);
		ResourceDto resources = ResourceDto.empty();
		model.addAttribute("resources", resources);
		return "admin/resource/detail";
	}

	@GetMapping(value = "/admin/resources/{id}")
	public String getResources(@PathVariable Long id, Model model) {
		List<Role> roleList = roleService.getRoles();
		model.addAttribute("roleList", roleList);
		Resources resources = resourcesService.getResources(id);

		ModelMapper modelMapper = new ModelMapper();
		ResourceDto resourcesDto = modelMapper.map(resources, ResourceDto.class);
		Set<Role> roleSet = resources.getResourcesRoleSet().stream()
			.map(ResourcesRole::getRole)
			.collect(Collectors.toSet());
		resourcesDto.setRoleSet(roleSet);
		model.addAttribute("resources", resourcesDto);
		return "admin/resource/detail";
	}

	@GetMapping(value = "/admin/resources/delete/{id}")
	public String removeResources(@PathVariable Long id, Model model) {
		resourcesService.getResources(id);
		resourcesService.deleteResources(id);
		urlAuthorizationManager.reload();
		return "redirect:/admin/resources";
	}
}
