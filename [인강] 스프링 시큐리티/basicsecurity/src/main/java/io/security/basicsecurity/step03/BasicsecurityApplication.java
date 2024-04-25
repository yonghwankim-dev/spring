package io.security.basicsecurity.step03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = BasicsecurityApplication.class)
public class BasicsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicsecurityApplication.class, args);
	}

}
