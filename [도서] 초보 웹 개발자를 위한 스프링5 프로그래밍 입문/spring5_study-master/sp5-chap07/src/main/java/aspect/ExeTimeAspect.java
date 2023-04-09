package aspect;



import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect	// 공통 기능 정의
public class ExeTimeAspect {
	@Pointcut("execution(public * chap07..*(..))")	// 공통기능 적용대상 설정, public으로 시작하는 메소드
	public void publicTarget()
	{
		
	}
	
	/**
	 * getSignature() : 호출한 메서드의 시그니처
	 * getTarget() : 대상 객체
	 * getArgs() : 인자 목록
	 * 
	 * @param joinPoint : 프록시의 대상 객체
	 * @return
	 * @throws Throwable
	 */
	@Around("publicTarget()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable
	{
		long start = System.nanoTime();	// 공통 기능의 예시, 시간측정
		try {
			Object result = joinPoint.proceed();	// 실제 대상 객체의 메서드 호출
			return result;
		}finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
								joinPoint.getTarget().getClass().getSimpleName(),
								sig.getName(),Arrays.toString(joinPoint.getArgs()),
								(finish-start));
		}
		
		
	}
}
