package com;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameSettings {

    private GameMode mode;
    private int maxPlayers;

    @Builder
    public GameSettings(GameMode mode, int maxPlayers) {
        this.mode = mode;
        this.maxPlayers = maxPlayers;
    }
}
