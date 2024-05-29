package io.security.corespringsecurity.security.factory.v5;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

	private LinkedHashMap<String, List<ConfigAttribute>> resourceMap;
	private final SecurityResourceService securityResourceService;
	private final String resourceType;

	public MethodResourcesMapFactoryBean(SecurityResourceService securityResourceService, String resourceType) {
		this.securityResourceService = securityResourceService;
		this.resourceType = resourceType;
	}

	private void init() {
		if ("method".equals(resourceType)) {
			resourceMap = securityResourceService.getMethodResourceList();
		} else if ("pointcut".equals(resourceType)) {
			resourceMap = securityResourceService.getPointcutResourceList();
		}
	}

	@Override
	public LinkedHashMap<String, List<ConfigAttribute>> getObject() {
		if (resourceMap == null) {
			init();
		}
		return resourceMap;
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

}
