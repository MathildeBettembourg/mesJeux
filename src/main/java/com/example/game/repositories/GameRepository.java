package com.example.game.repositories;

import com.example.game.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface GameRepository  extends JpaRepository<Game, Long> {
Game findGameByDateOfGame(Date date);
//Game findGameByListOfWinnerIsContaining(String firstName);
}
