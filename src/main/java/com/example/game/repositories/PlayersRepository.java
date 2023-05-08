package com.example.game.repositories;

import com.example.game.entities.Players;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayersRepository  extends JpaRepository<Players,Long> {
    List<Players> findPlayersByFirstName(String firstName);
    List<Players> findPlayersByLastName(String lastName);
}
