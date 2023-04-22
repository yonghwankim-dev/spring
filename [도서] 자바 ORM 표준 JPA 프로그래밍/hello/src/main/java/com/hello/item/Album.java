package com.hello.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class Album extends Item {

    final String artist;

    public Album(Long id, String name, int price, String artist) {
        super(id, name, price);
        this.artist = artist;
    }
}
