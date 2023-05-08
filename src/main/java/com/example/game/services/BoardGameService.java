package com.example.game.services;

import com.example.game.entities.BoardGame;
import com.example.game.utils.BoardGameDTOOut;
import com.example.game.utils.MyEnum;

import java.util.List;

public interface BoardGameService {
    /**
     * findAll() is a function to find all the borad games of the database
     *
     * @return a list of boardgame
     */
    List<BoardGameDTOOut> findAll();

    /**
     * findAllById to find a list of borad game by their ids
     *
     * @param longs id list
     * @return boardgames as an iterable (list though)
     */
    List<BoardGameDTOOut> findAllById(Iterable<Long> longs);

    /**
     * this function is to save a new game in database
     *
     * @param entity a game as Boardgame object
     * @return the entity saved
     */
    BoardGame save(BoardGame entity);

    /**
     * This function is made to find a game by its id
     *
     * @param id long
     * @return an object corresponding to the targetted one
     */
    BoardGame findById(Long id);

    /**
     * Common util function
     *
     * @param id to check if something exist in the database
     * @return a boardGame
     */
    boolean existsById(Long id);

    /**
     * Common util function
     *
     * @return a number of database name
     */
    long count();

    /**
     * Common util function to delete a boardgame within database following its id
     *
     * @param id long from the boardgame
     */
    void deleteById(Long id);

    /**
     * find by number of players min to find the right game according the number of players
     *
     * @param numberOfPlayerMin as an interger
     * @return a list og boardgames
     */
    List<BoardGameDTOOut> findByNumberOfPlayersMin(Integer numberOfPlayerMin);

    /**
     * find by number of players min to find the right game according the number of players
     *
     * @param numberOfPlayerMax as an interger
     * @return a list og boardgames
     */
    List<BoardGameDTOOut> findByNumberOfPlayersMax(Integer numberOfPlayerMax);

    /**
     * find by time is to find the right
     *
     * @param time that games have to playahead
     * @return a list of boardgame
     */
    List<BoardGameDTOOut> findByTime(Integer time);

    /**
     * find by experience of the players, the lower have to be took into account here
     *
     * @param experience as a sting
     * @return a list of borad game according to their experience
     */
    List<BoardGameDTOOut> findByExperience(MyEnum.Experience experience);

    /**
     * update to update a boradgame
     * @param boardGame as a gamehere
     * @return the updated boadgame
     */
    BoardGame update(BoardGame boardGame);
//   BoardGame addGameIdToBoardGameHistoric(Long gameId, Long boardGameId);
//    BoardGame addPlayerThatPlayerToThatBoardGame(Long playerId, Long boardgameId);

    List<BoardGameDTOOut> listByType(MyEnum.Type type);
}
