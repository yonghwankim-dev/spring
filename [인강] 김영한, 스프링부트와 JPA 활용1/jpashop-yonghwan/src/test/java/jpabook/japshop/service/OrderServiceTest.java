package jpabook.japshop.service;

import jpabook.japshop.domain.*;
import jpabook.japshop.exception.NotEnoughStockException;
import jpabook.japshop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(getOrder.getTotalPrice()).isEqualTo(10000 * orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(8);
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book = Book.builder()
                        .name(name)
                        .price(price)
                        .stockQuantity(stockQuantity)
                        .build();

        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = Member.builder()
                              .name("회원1")
                              .address(new Address("서울","강가","123-123"))
                              .build();
        em.persist(member);
        return member;
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;

        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        //then
        fail("재고수량 부족 예외가 발생해야 한다.");
    }
    
    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL); // 주문취소시 상태는 CANCEL이다
        assertThat(book.getStockQuantity()).isEqualTo(10);      //주문이 취소된 상품은 그만큼 재고가 증가해야한다.
    }
    

}