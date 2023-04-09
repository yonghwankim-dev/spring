package jpabook.japshop.domain;

import jpabook.japshop.exception.NotEnoughStockException;
import jpabook.japshop.service.UpdateItemDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private final List<Category> categories = new ArrayList<Category>();

    //==비즈니스 로직==//
    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        if(!isEnoughStock(quantity)){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity -= quantity;
    }

    //==조회 로직==//
    /**
     * 재조가 충분한지 조회
     */
    private boolean isEnoughStock(int quantity){
        return this.stockQuantity - quantity >= 0 ? true : false;
    }

    //==수정 로직==//
    public void changeInfo(UpdateItemDto updateItemDto){
        this.name = updateItemDto.getName();
        this.price = updateItemDto.getPrice();
        this.stockQuantity = updateItemDto.getStockQuantity();
    }
}
