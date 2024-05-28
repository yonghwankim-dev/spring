package io.security.corespringsecurity.security.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private final String errorPage;

	public CustomAccessDeniedHandler(String errorPage) {
		this.errorPage = errorPage;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		String exceptionMessage = URLEncoder.encode(accessDeniedException.getMessage(), StandardCharsets.UTF_8);
		String deniedUrl = errorPage + "?exception=" + exceptionMessage;
		response.sendRedirect(deniedUrl);
	}
}
