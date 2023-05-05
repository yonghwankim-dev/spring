package hello.core.scan.filter;

import static org.springframework.context.annotation.ComponentScan.*;

import java.util.Base64;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    @Test
    public void filterScan() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            ComponentFilterAppConfig.class);
        //when
        BeanA beanA = ctx.getBean(BeanA.class);
        Throwable throwable = Assertions.catchThrowable(() -> ctx.getBean(BeanB.class));
        //then
        Assertions.assertThat(beanA).isNotNull();
        Assertions.assertThat(throwable).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Configuration
    @ComponentScan(
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class),
        includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class))
    static class ComponentFilterAppConfig {

    }

    @Test
    public void filterScanWithAssignableFilterType() {
        //given
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            ComponentFilterAppConfig2.class);
        //when
        BeanA beanA = ctx.getBean(BeanA.class);
        Throwable throwable = Assertions.catchThrowable(() -> ctx.getBean(BeanB.class));
        //then
        Assertions.assertThat(beanA).isNotNull();
        Assertions.assertThat(throwable).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Configuration
    @ComponentScan(
        excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanB.class),
        includeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BeanA.class))
    static class ComponentFilterAppConfig2 {

    }

}
