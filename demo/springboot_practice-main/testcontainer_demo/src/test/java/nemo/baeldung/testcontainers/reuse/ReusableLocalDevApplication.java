package nemo.baeldung.testcontainers.reuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import nemo.baeldung.testcontainers.Application;

public class ReusableLocalDevApplication {
	public static void main(String[] args) {
		SpringApplication.from(Application::main)
			.with(LocalDevTestContainersConfig.class)
			.run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class LocalDevTestContainersConfig{
		@Bean
		@RestartScope
		@ServiceConnection
		public MongoDBContainer mongoDBContainer(){

			try(MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
				.withReuse(true)){
				return container;
			}
		}
	}
}
