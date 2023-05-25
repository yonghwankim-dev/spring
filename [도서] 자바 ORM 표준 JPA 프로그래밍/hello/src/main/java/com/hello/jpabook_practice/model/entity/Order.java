package com.hello.jpabook_practice.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
@TableGenerator(
    name = "ORDER_SEQ_GENERATOR",
    table = "JPABOOK_SEQUENCES",
    pkColumnValue = "ORDER_SEQ"
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDER_SEQ_GENERATOR")
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문상태

    //==연관관계 메소드==//
    public void setMember(Member member) {
        // 연관관계 제거
        if (this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        if (this.member != null) {
            this.member.getOrders().add(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
