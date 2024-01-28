package nemo.interview.class01.ioc.step1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 스프링 컨테이너에 XML 파일 설정 정보를 이용하여 등록하기
 */
public class IoCContainerExample01 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Car car = (Car)ctx.getBean("car");
		System.out.println(car.getName());
	}
}
