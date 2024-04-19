package com;

import lombok.Data;

@Data
public class GameDTO {

    private Long id;
    private String name;
    private Long creationTime;
    private String creator;
    private int totalPlayers;
    private GameMode mode;
    private int maxPlayers;
}
