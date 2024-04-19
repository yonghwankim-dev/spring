package nemo.filter.registration.step04;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@WebFilter(urlPatterns = {"/filterData/*"})
public class CustomRequestFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Custom Request init START");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletRequest req = (HttpServletRequest)request;

		// GET 방식 요청 중 '/filterData' 경로에 대해서만 파라미터 변경
		if (req.getMethod().equals("GET") && req.getRequestURI().equals("/filterData")) {
			// 요청을 위한 커스텀 래퍼(wrapper) 생성
			CustomRequestWrapper requestWrapper = new CustomRequestWrapper(req){
				@Override
				public String getServerName() {
					return "test.com";
				}
			};

			// 원하는 대로 파라미터 수정
			requestWrapper.setParameter("name", req.getParameter("name"));
			requestWrapper.setParameter("age", req.getParameter("age"));
			requestWrapper.setParameter("user", "1");

			// 수정된 요청으로 계속 진행
			log.info("CustomRequestFilter Start");
			chain.doFilter(requestWrapper, response);
			log.info("CustomRequestFilter End");
		} else {
			// 다른 요청에 대해서는 기존 요청 그대로 전달
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		log.info("Custom Request init Destory");
	}
}
