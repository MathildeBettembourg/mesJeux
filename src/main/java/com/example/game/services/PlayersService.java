package com.example.game.services;

import com.example.game.entities.BoardGame;
import com.example.game.entities.Game;
import com.example.game.entities.Players;
import com.example.game.utils.BoardGameDTOOut;
import com.example.game.utils.GameDTOOut;
import com.example.game.utils.PlayersDTOOut;

import java.util.List;

public interface PlayersService {
    /**
     * findall o find all the player of the database
     *
     * @return alist of players
     */
    List<PlayersDTOOut> findAll();

    /**
     * find all to find a list of players by their ids
     *
     * @param longs list of ids
     * @return the list of corresponding players
     */
    List<Players> findAllById(List<Long> longs);

    /**
     * save to save in database a player
     *
     * @param entity as a Player
     * @return the player saved in database
     */
    Players save(Players entity);

    /**
     * a function to find by id a player
     *
     * @param id as long
     * @return a player
     */
    Players findById(Long id);

    /**
     * commun util function here to check if it exist in database
     *
     * @param id long
     * @return a boolean
     */
    boolean existsById(Long id);

    /**
     * commun util function
     *
     * @return a number
     */
    long count();

    /**
     * delete by id is a function to delete a player following its id
     *
     * @param id long the one of the player targeted
     */
    void deleteById(Long id);

    /**
     * findPlayerByFirstName to find a player easily by its firstname
     *
     * @param firstName as a string
     * @return the targeted player
     */
    List<Players> findPlayersByFirstName(String firstName);

    /**
     * findPlayerByLastName to find a player easily by its lastname
     *
     * @param lastName string
     * @return the targetedplayer
     */
    List<Players> findPlayersByLastName(String lastName);

    List<BoardGameDTOOut> findAllGameOfOnePlayer(Long id);

    Players update(Players players);

//    Players addGameToPlayer(Long playerId, Long gameId);
//
//    Players addBoardGameToPlayer(Long playerId, Long boardGameId);
//
//    Players addWinningEvent(Long playerId, Long gameId);
    List<GameDTOOut>findAllGameWon(Long id);
    List<GameDTOOut> getListGamePlayed(Long playerId);
}
