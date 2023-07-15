package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.oauth.InMemoryProviderRepository;
import com.oauth.OauthProvider;

@Component
public class Oauth2DemoApplicationRunner implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Oauth2DemoApplicationRunner.class);

	private final InMemoryProviderRepository providerRepository;

	public Oauth2DemoApplicationRunner(InMemoryProviderRepository providerRepository) {
		this.providerRepository = providerRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		OauthProvider github = providerRepository.findByProviderName("github");
		logger.info("github: " + github);
	}
}
