package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
        AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타 정보 확인하기")
    public void findApplicationBean() {
        //given
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        //when
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ctx.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println(
                    "beanDefinitionName = " + beanDefinitionName + ", beanDefinition = "
                        + beanDefinition);
            }
        }
        //then
    }
}
