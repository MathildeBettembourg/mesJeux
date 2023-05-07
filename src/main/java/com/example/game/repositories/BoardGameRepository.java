package com.example.game.repositories;

import com.example.game.entities.BoardGame;
import com.example.game.utils.MyEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    List<BoardGame> findByNbOfPlayersMinIsLessThanEqual(Integer nbOfPlayersMin);
    List<BoardGame> findByNbOfPlayersMaxIsLessThanEqual(Integer nbOfPlayersMax);

    List<BoardGame> findByTime(Integer time);
    List<BoardGame> findByExperience(MyEnum.Experience experience);
    List<BoardGame> findBoardGameByType(MyEnum.Type type);

}
