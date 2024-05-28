package io.security.corespringsecurity.security.config.v5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import io.security.corespringsecurity.security.factory.v5.MethodResourcesFactoryBean;
import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableGlobalMethodSecurity
@RequiredArgsConstructor
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	private final SecurityResourceService securityResourceService;
 
	@Override
	protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
		return mapBasedMethodSecurityMetadataSource();
	}

	@Bean
	protected MapBasedMethodSecurityMetadataSource mapBasedMethodSecurityMetadataSource() {
		return new MapBasedMethodSecurityMetadataSource(methodResourcesFactoryBean().getObject());
	}

	protected MethodResourcesFactoryBean methodResourcesFactoryBean() {
		return new MethodResourcesFactoryBean(securityResourceService);
	}
}
