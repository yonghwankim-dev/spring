package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanTest {

    private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
        SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다.")
    public void findBeanByTypeDuplicate() {
        //given
        Class<MemberRepository> beanType = MemberRepository.class;
        //when
        Throwable throwable = Assertions.catchThrowable(() -> ctx.getBean(beanType));
        //then
        Assertions.assertThat(throwable).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    @DisplayName("동일한 타입이 둘 이상 있으면 빈 이름을 지정하면 된다")
    public void findBeanByName() {
        //given
        String beanName = "memberRepository1";
        //when
        MemberRepository memberRepository = ctx.getBean(beanName, MemberRepository.class);
        //then
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 빈 타입을 모두 조회하기")
    public void findAllBeanByType() {
        //given
        Class<MemberRepository> beanType = MemberRepository.class;
        //when
        Map<String, MemberRepository> beansOfType = ctx.getBeansOfType(beanType);
        //then
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
