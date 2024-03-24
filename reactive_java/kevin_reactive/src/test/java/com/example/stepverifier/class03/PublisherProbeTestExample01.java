package com.example.stepverifier.class03;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class PublisherProbeTestExample01 {

	@Test
	void test(){
		PublisherProbe<String> probe = PublisherProbe.of(PublisherProbeExample.useStandbyPower());

		StepVerifier
			.create(PublisherProbeExample.processWith(PublisherProbeExample.useMainPower(), probe.mono()))
			.expectNextCount(1)
			.verifyComplete();

		probe.assertWasSubscribed();
		probe.assertWasRequested();
		probe.assertWasNotCancelled();
	}
}
