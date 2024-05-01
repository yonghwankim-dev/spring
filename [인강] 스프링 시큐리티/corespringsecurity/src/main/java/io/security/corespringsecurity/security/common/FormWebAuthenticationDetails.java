package io.security.corespringsecurity.security.common;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {

	private final String secretKey;

	public FormWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		this.secretKey = request.getParameter("secret_key");
	}

}
