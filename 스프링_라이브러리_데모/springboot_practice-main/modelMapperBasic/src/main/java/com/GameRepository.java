package com;

import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class GameRepository {

    public Game findById(Long id) {
        return Game.builder().id(id).build();
    }
}
