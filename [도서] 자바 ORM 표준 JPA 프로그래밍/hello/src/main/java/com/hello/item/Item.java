package com.hello.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Item {

    final Long id;
    final String name;
    final int price;
}
