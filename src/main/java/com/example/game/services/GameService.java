package com.example.game.services;

import com.example.game.entities.Game;
import com.example.game.utils.GameDTOOut;

import java.util.List;

public interface GameService {
    /**
     * This function is to add a player to a game
     *
     * @param gameId    the id of the game
     * @param playersId the id of the player
     * @return the game edited
     */
    Game addPlayerToGame(Long gameId, List<Integer> playersId);

    /**
     * FindAll is the function to find all the game in database
     *
     * @return a list of dtoGAME
     */
    List<GameDTOOut> findAll();

    /**
     * It is a function to save a game in the database
     *
     * @param game GAME from the input
     * @return the new game
     */
    Game save(Game game);

    /**
     * A function to find a game by its id
     *
     * @param id og the game Long
     * @return the game or a message if not found
     */
    Game findById(Long id);

    /**
     * ExistById is a function to check if a game exist by its id in the database
     *
     * @param id Long of a game
     * @return a boolean, true or false
     */
    boolean existsById(Long id);

    /**
     * To delete a game in the database
     *
     * @param aLong the if of the game
     */
    void deleteById(Long aLong);
}
