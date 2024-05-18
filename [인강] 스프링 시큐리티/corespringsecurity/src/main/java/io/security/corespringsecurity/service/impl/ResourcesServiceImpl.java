package io.security.corespringsecurity.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.repository.ResourcesRepository;
import io.security.corespringsecurity.service.ResourcesService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResourcesServiceImpl implements ResourcesService {

	private final ResourcesRepository resourceRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Resources> getResources() {
		return resourceRepository.findAll(Sort.by(Sort.Order.asc("orderNum")));
	}

	@Transactional(readOnly = true)
	@Override
	public Resources getResources(long id) {
		return resourceRepository.findResourcesById(id).orElseGet(Resources::new);
	}

	@Transactional
	@Override
	public void createResources(Resources resources) {
		resourceRepository.save(resources);
	}

	@Transactional
	@Override
	public void deleteResources(long id) {
		resourceRepository.deleteById(id);
	}
}
