package jpabook.japshop.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "order_item")
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // 주문상품

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문

    private int orderPrice; // 주문가격
    private int count;      // 주문수량


    //==연관관계 메서드==//
    public void setOrder(Order order){
        this.order = order;
    }

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = OrderItem.builder()
                                       .item(item)
                                       .orderPrice(orderPrice)
                                       .count(count)
                                       .build();

        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel(){
        this.item.addStock(count);
    }

    //==조회 로직==//
    public int getTotalPrice(){
        return orderPrice * count;
    }
}
