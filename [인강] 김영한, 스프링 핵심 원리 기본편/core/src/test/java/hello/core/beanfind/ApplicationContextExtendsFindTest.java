package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {

    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
        TestConfig.class);

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    @Test
    @DisplayName("부모 타입으로 빈 조회시 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    public void findBeanByParentTypeDuplicate() {
        //given
        Class<DiscountPolicy> beanType = DiscountPolicy.class;
        //when
        Throwable throwable = Assertions.catchThrowable(() -> ctx.getBean(beanType));
        //then
        Assertions.assertThat(throwable).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("부모 타입으로 빈 조회시 자식이 둘 이상 있으면 빈 이름으로 조회하면 된다..")
    public void findBeanByParentTypeBeanName() {
        //given
        String beanName = "rateDiscountPolicy";
        //when
        DiscountPolicy discountPolicy = ctx.getBean(beanName, DiscountPolicy.class);
        //then
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회 (비권장)")
    public void findBeanByChildType() {
        //given
        Class<RateDiscountPolicy> beanType = RateDiscountPolicy.class;
        //when
        DiscountPolicy discountPolicy = ctx.getBean(beanType);
        //then
        Assertions.assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    public void findBeanByParentType() {
        //given
        Class<DiscountPolicy> beanType = DiscountPolicy.class;
        //when
        Map<String, DiscountPolicy> beansOfType = ctx.getBeansOfType(beanType);
        //then
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
    }

    @Test
    @DisplayName("부모 타입으로 조회하기 - Object")
    public void findAllBeanByObjectType() {
        //given
        Class<Object> beanType = Object.class;
        //when
        Map<String, Object> beansOfType = ctx.getBeansOfType(beanType);
        //then
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
    }
}
