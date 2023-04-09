package jpabook.japshop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name="orders")
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 주문 회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private final List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; // 배송상태

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 [ORDER, CANCEL]


    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = Order.builder()
                           .status(OrderStatus.ORDER)
                           .orderDate(LocalDateTime.now())
                           .build();

        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }

        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문취소
     */
    public void cancel(){
        if(isCompleteDelivery()){
            throw new IllegalStateException("이미 배송된 상품은 취소가 불가능합니다.");
        }
        this.status = OrderStatus.CANCEL;
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }


    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice(){
        return orderItems.stream()
                         .mapToInt(OrderItem::getTotalPrice)
                         .sum();
    }

    /**
     * 배송 완료 여부조회
     */
    private boolean isCompleteDelivery(){
        return delivery.getStatus() == DeliveryStatus.COMP ? true : false;
    }



}
