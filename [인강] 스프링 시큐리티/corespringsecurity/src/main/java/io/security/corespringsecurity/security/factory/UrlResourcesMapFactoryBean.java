package io.security.corespringsecurity.security.factory;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.web.util.matcher.RequestMatcher;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UrlResourcesMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<String>>> {

	private LinkedHashMap<RequestMatcher, List<String>> resourceMap;
	private SecurityResourceService securityResourceService;

	public void setSecurityResourceService(SecurityResourceService securityResourceService) {
		this.securityResourceService = securityResourceService;
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<String>> getObject() {
		if (resourceMap == null) {
			init();
		}
		log.info("resourceMap : {}", resourceMap);
		return resourceMap;
	}

	private void init() {
		resourceMap = securityResourceService.getResourceList();
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}
}
