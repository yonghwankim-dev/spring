package kr.yh;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class PerfAspect {

    // Pointcut 표현식 방법 1 : execution
    @Around("execution(* kr.yh.EventService.*(..))") // Pointcut : 어디에 적용할 것인가?
    public Object  logPerf(ProceedingJoinPoint pjp) throws Throwable { // Advice : 해야할 일
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }

    // 포인트컷 표현식 방법 2 : @annotation (권장)
    // PerfLogging이 붙은 메서드만 Advice 수행
    @Around("@annotation(PerfLogging)")
    public Object logNow(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(LocalDateTime.now());
        Object retVal = pjp.proceed();
        return retVal;
    }

    // 포인트컷 표현식 방법 3 : bean
    @Around("bean(simpleEventService)")
    public Object logCallMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("call method");
        Object retVal = pjp.proceed();
        return retVal;
    }

    @Before("@annotation(PerfLogging)")
    public void helloBefore(){
        System.out.println("hello before");
    }

    @AfterReturning("@annotation(PerfLogging)")
    public void helloAfterReturning(){
        System.out.println("hello afterReturning");
    }

    @AfterThrowing("@annotation(PerfLogging)")
    public void helloAfterThrowing(){
        System.out.println("hello afterThrowing");
    }
}
