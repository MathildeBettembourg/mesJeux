package com.example.game.repositories;

import com.example.game.entities.Players;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository  extends JpaRepository<Players,Long> {
    Players findPlayersByFirstName(String firstName);
    Players findPlayersByLastName(String lastName);
}
