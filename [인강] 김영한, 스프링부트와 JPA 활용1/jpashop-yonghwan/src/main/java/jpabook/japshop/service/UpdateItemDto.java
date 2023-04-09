package jpabook.japshop.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class UpdateItemDto {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
}
