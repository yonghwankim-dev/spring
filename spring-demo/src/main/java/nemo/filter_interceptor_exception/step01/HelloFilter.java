package nemo.filter_interceptor_exception.step01;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = {"/filter/hello"})
public class HelloFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("Custom Request init START");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {
		HttpServletResponse servletResponse = (HttpServletResponse)response;

		try{
			log.info("CustomRequestFilter Start");
			throw new RuntimeException("error");
			// chain.doFilter(request, response);
			// log.info("CustomRequestFilter End");
		}catch (Exception e){
			servletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			servletResponse.getWriter().write("this is BadRequest");
		}
	}

	@Override
	public void destroy() {
		log.info("Custom Request init Destory");
	}
}
