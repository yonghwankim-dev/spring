package io.security.corespringsecurity.security.config.v5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import io.security.corespringsecurity.security.factory.v5.MethodResourcesMapFactoryBean;
import io.security.corespringsecurity.security.processor.ProtectPointcutPostProcessor;
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
		return new MapBasedMethodSecurityMetadataSource(methodResourcesMapFactoryBean().getObject());
	}

	@Bean
	protected MethodResourcesMapFactoryBean methodResourcesMapFactoryBean() {
		return new MethodResourcesMapFactoryBean(securityResourceService, "method");
	}

	@Bean
	protected MethodResourcesMapFactoryBean pointcutResourcesMapFactoryBean() {
		return new MethodResourcesMapFactoryBean(securityResourceService, "pointcut");
	}

	@Bean
	protected ProtectPointcutPostProcessor protectPointcutPostProcessor() {
		ProtectPointcutPostProcessor protectPointcutPostProcessor = new ProtectPointcutPostProcessor(
			mapBasedMethodSecurityMetadataSource());
		protectPointcutPostProcessor.setPointcutMap(pointcutResourcesMapFactoryBean().getObject());
		return protectPointcutPostProcessor;
	}

	// @Bean
	// BeanPostProcessor protectPointcutPostProcessor() throws
	// 	ClassNotFoundException,
	// 	NoSuchMethodException,
	// 	InvocationTargetException,
	// 	InstantiationException,
	// 	IllegalAccessException {
	//
	// 	Class<?> clazz = Class.forName("org.springframework.security.config.method.ProtectPointcutPostProcessor");
	// 	Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(MapBasedMethodSecurityMetadataSource.class);
	// 	declaredConstructor.setAccessible(true);
	// 	Object instance = declaredConstructor.newInstance(mapBasedMethodSecurityMetadataSource());
	// 	Method setPointcutMap = instance.getClass().getMethod("setPointcutMap", Map.class);
	// 	setPointcutMap.setAccessible(true);
	// 	setPointcutMap.invoke(instance, pointcutResourcesMapFactoryBean().getObject());
	//
	// 	return (BeanPostProcessor)instance;
	// }
}
