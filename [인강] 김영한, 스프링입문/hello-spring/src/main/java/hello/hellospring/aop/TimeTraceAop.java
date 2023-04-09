package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Aop는 빈으로 등록되어야함
@Aspect
@Component
public class TimeTraceAop {

    // 패키지명..클래스명(타입)
    // ex) "execution(* hello.hellospring.service..*(..)) 서비스 하위 패키지에만 시간 추적 aop 적용
    @Around("execution(* hello.hellospring..*(..))") // 공통 관심사항을 어디에 적용할 것인지 선택
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.printf("START: %s%n", joinPoint);
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.printf("END: %s %dms%n", joinPoint, timeMs);
        }
    }

}
