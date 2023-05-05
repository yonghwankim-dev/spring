package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
        AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean() {
        //given
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        //when
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ctx.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
        //then
    }

    @Test
    @DisplayName("애플리케이션 빈 출력")
    public void findApplicationBean() {
        //given
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        //when
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ctx.getBeanDefinition(beanDefinitionName);

            // 스프링 내부에서 등록된 빈이 아닌 내가 직접 등록한 빈을 출력합니다.
            // ROLE ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // ROLE ROLE_INFRASTRUCTURE: 스프링 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ctx.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }
        }
        //then
    }
}
