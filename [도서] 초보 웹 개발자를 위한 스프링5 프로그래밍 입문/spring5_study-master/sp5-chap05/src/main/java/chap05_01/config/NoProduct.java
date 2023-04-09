package chap05_01.config;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// 특정 애노테이션을 붙인 타입을 컴포넌트 대상에서 제외할 수도 있다.
// 예를 들어 @NoProduct 애노테이션을 붙인 클래스는 컴포넌트 스캔 대상에서 제외할 수 있다.
@Retention(RUNTIME)
@Target(TYPE)
public @interface NoProduct {

}
