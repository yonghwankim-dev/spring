package io.security.corespringsecurity.security.factory.v5;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodResourcesFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

	private LinkedHashMap<String, List<ConfigAttribute>> resourceMap;
	private final SecurityResourceService securityResourceService;

	public MethodResourcesFactoryBean(SecurityResourceService securityResourceService) {
		this.securityResourceService = securityResourceService;
	}

	private void init() {
		resourceMap = securityResourceService.getMethodResourceList();
	}

	@Override
	public LinkedHashMap<String, List<ConfigAttribute>> getObject() {
		if (resourceMap == null) {
			init();
		}
		log.info("resourceMap : {}", resourceMap);
		return resourceMap;
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

}
