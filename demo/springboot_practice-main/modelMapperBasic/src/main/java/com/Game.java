package com;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game {

    private Long id;
    private String name;
    private Long timestamp;

    private Player creator;
    private List<Player> players = new ArrayList<>();

    private GameSettings settings;

    @Builder
    public Game(Long id, String name, Long timestamp, Player creator, GameSettings settings) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.creator = creator;
        this.settings = settings;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
