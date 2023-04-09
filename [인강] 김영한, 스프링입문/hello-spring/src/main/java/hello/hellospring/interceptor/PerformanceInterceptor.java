package hello.hellospring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PerformanceInterceptor implements HandlerInterceptor{

    private static final Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);
    private static final String START_TIME = "START_TIME";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        request.setAttribute(START_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute(START_TIME);
        logger.info("execution time : {}", System.currentTimeMillis() - startTime);
    }
}
