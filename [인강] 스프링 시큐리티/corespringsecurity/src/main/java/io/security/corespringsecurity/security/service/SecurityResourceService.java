package io.security.corespringsecurity.security.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.security.corespringsecurity.domain.entity.AccessIp;
import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.repository.AccessIpRepository;
import io.security.corespringsecurity.repository.ResourcesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityResourceService {

	private final ResourcesRepository repository;
	private final AccessIpRepository accessIpRepository;

	@Transactional(readOnly = true)
	public LinkedHashMap<RequestMatcher, List<String>> getResourceList() {
		LinkedHashMap<RequestMatcher, List<String>> result = new LinkedHashMap<>();

		List<Resources> resourcesList = repository.findAllResources();
		for (Resources resource : resourcesList) {
			result.put(new AntPathRequestMatcher(resource.getResourceName()), resource.getRoleNames());
		}
		return result;
	}

	@Transactional(readOnly = true)
	public List<String> getAccessIpList() {
		return accessIpRepository.findAll().stream()
			.map(AccessIp::getIpAddress)
			.toList();
	}
}
