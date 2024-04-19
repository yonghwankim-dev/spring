package com;

import lombok.Builder;
import lombok.Data;

@Data
public class Player {

    private Long id;
    private String name;
    private Game currentGame;

    @Builder
    public Player(Long id, String name, Game currentGame) {
        this.id = id;
        this.name = name;
        this.currentGame = currentGame;
    }
}
