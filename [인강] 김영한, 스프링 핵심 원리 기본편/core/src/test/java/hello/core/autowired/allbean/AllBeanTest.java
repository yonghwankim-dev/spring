package hello.core.autowired.allbean;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

    private static final Logger log = LoggerFactory.getLogger(AllBeanTest.class);

    @Test
    public void findAllBean() {
        //given
        AnnotationConfigApplicationContext ctx =
            new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ctx.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        Member member2 = new Member(2L, "userB", Grade.BASIC);
        //when
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        int discountPrice2 = discountService.discount(member2, 20000, "rateDiscountPolicy");
        //then
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);
        Assertions.assertThat(discountPrice2).isEqualTo(2000);
    }

    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        public DiscountService(Map<String, DiscountPolicy> policyMap,
            List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            log.info("policyMap={}", policyMap);
            log.info("policyList={}", policyList);
        }

        public int discount(Member member, int itemPrice, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, itemPrice);
        }
    }
}
