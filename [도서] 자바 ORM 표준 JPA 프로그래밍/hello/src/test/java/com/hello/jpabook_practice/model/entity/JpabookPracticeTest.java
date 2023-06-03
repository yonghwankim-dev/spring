package com.hello.jpabook_practice.model.entity;

import static com.hello.jpabook_practice.model.entity.DeliveryStatus.*;
import static com.hello.jpabook_practice.model.entity.OrderStatus.*;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hello.jpabook_practice.model.entity.item.Album;
import com.hello.jpabook_practice.model.entity.item.Book;
import com.hello.jpabook_practice.model.entity.item.Item;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JpabookPracticeTest {


    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook_practice");
    }

    @BeforeEach
    public void beforeEach() {
        em = emf.createEntityManager();
    }

    @Test
    @DisplayName("엔티티 객체 초기화")
    public void test1() {
        // given
        Member member = new Member();
        Delivery delivery = new Delivery();
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        Item jpa = new Book();
        Category book = new Category();
        Category it = new Category();
        CategoryItem categoryItem = new CategoryItem();

        member.setName("김용환");
        member.setCity("서울");
        member.setZipcode("29000");

        delivery.setDeliveryStatus(READY);
        delivery.setCity("서울");
        delivery.setStreet("강남구");
        delivery.setZipcode("29000");

        order.setMember(member);
        order.setOrderStatus(ORDER);
        order.setOrderDate(new Date());
        order.setDelivery(delivery);
        order.addOrderItem(orderItem);

        orderItem.setOrder(order);
        orderItem.setItem(jpa);
        orderItem.setCount(2);
        orderItem.setOrderPrice(jpa.getPrice() * orderItem.getCount());

        book.addChildCategory(it);
        it.setParent(book);
        it.setName("IT");

        categoryItem.setCategory(it);
        categoryItem.setItem(jpa);

        jpa.setName("JPA");
        jpa.setPrice(10000);
        jpa.setStockQuantity(5);
        jpa.addCategoryItem(categoryItem);

        // when
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(member);
        em.persist(delivery);
        em.persist(order);
        em.persist(book);
        em.persist(it);
        em.persist(jpa);
        em.persist(categoryItem);
        em.persist(orderItem);

        em.flush();
        // then
        Member findMember = em.find(Member.class, member.getId());
        Order findOrder = findMember.getOrders().stream().findAny().orElseThrow();
        Delivery findDelivery = findOrder.getDelivery();
        OrderItem findOrderItem = findOrder.getOrderItems().stream().findAny().orElseThrow();
        Item findItem = findOrderItem.getItem();
        CategoryItem findCategoryItem = findItem.getCategoryItems().stream().findAny().orElseThrow();

        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(findMember).isEqualTo(member);
        assertions.assertThat(findOrder).isEqualTo(order);
        assertions.assertThat(findDelivery).isEqualTo(delivery);
        assertions.assertThat(findOrderItem).isEqualTo(orderItem);
        assertions.assertThat(findItem).isEqualTo(jpa);
        assertions.assertThat(findCategoryItem).isEqualTo(categoryItem);
        assertions.assertThat(findCategoryItem.getCategory()).isEqualTo(it);

        transaction.rollback();
    }
}
