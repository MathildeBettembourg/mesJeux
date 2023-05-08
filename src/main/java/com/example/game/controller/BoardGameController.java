package com.example.game.controller;

import com.example.game.entities.BoardGame;
import com.example.game.services.BoardGameService;
import com.example.game.utils.BoardGameDTOOut;
import com.example.game.utils.MyEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController
@RequestMapping("/boardgame")
public class BoardGameController {

    private BoardGameService boardGameService;

    public BoardGameController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    /**
     * findAll() is a function to find all the boardgames of the database
     * @return a list of boardgames -*
     */
    @GetMapping("")
    public List<BoardGameDTOOut> findAll() {
        return boardGameService.findAll();
    }

//    /**
//     * to add a player to a boardgame file
//     * @param boardgameId long
//     * @param playerId long
//     * @return the boardgame edited
//     */
//    @PostMapping("/boardgames/{boardgameId}/players/{playerId}")
//    public BoardGame addPlayerThatPlayerToThatBoardGame (@PathVariable Long boardgameId, @PathVariable Long playerId) {
//        return this.boardGameService.addPlayerThatPlayerToThatBoardGame(playerId, boardgameId);
//    }

//    //**many to one game and board game
//    @PostMapping("/boardgames/{boardGameId}/games/{gameId}")
//    public BoardGame addGameIdToBoardGameHistoric(@PathVariable Long boardGameId, @PathVariable Long gameId) {
//        return this.boardGameService.addGameIdToBoardGameHistoric(gameId, boardGameId);
//    }


    /**
     * this function is to save a new game in database
     *
     * @param entity a game as Boardgame object
     * @return the entity saved+
     */
    @PostMapping("")
    public BoardGame save(@RequestBody BoardGame entity) {
        return boardGameService.save(entity);
    }

    /**
     * This function is made to find a game by its id+
     *
     * @param id long
     * @return an object corresponding to the targetted one
     */
    @GetMapping("/{id}")
    public BoardGame findById(@PathVariable Long id) {
        return boardGameService.findById(id);
    }


    /**
     * Common util function to delete a boardgame within database following its id
     *
     * @param id long from the boardgame+
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        boardGameService.deleteById(id);
    }

    /**
     * find by number of players min to find the right game according the number of players
     *
     * @param numberOfPlayerMin as an interger+
     * @return a list og boardgames
     */
    @GetMapping("/numberOfPlayersMin/{numberOfPlayerMin}")
    public List<BoardGameDTOOut> findByNumberOfPlayersMin(@PathVariable Integer numberOfPlayerMin) {
        return boardGameService.findByNumberOfPlayersMin(numberOfPlayerMin);
    }

    /**
     * find by number of players min to find the right game according the number of players
     *
     * @param numberOfPlayerMax as an interger+
     * @return a list og boardgames
     */
    @GetMapping("/numberOfPlayersMax/{numberOfPlayerMax}")
    public List<BoardGameDTOOut> findByNumberOfPlayersMax(@PathVariable Integer numberOfPlayerMax) {
        return boardGameService.findByNumberOfPlayersMax(numberOfPlayerMax);
    }

    /**
     * find by time is to find the right
     *
     * @param time that games have to played+
     * @return a list of boardgame
     */
    @GetMapping("/time/{time}")
    public List<BoardGameDTOOut> findByTime(@PathVariable Integer time) {
        return boardGameService.findByTime(time);
    }

    /**
     * find by experience of the players, the lower have to be took into account here
     *
     * @param experience as a sting
     * @return a list of borad game according to their experience
     */
    @GetMapping("/experience/{experience}")
    public List<BoardGameDTOOut> findByExperience(@PathVariable MyEnum.Experience experience) {
        return boardGameService.findByExperience(experience);
    }

    /**
     * find and update an object
     *
     * @param boardGame the game to change in bdd
     * @param id        of the game
     * @return the modified game
     */
    @PostMapping("/{id}")
    public BoardGame updateBoardGame(@RequestBody BoardGame boardGame, @PathVariable Long id) {
        if (!id.equals(boardGame.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "erreur de correspondence entre l'objets à modifier et son id en BDD");
        }
        return this.boardGameService.update(boardGame);
    }
    @GetMapping("/type/{type}")
    public List<BoardGameDTOOut> listByType(@PathVariable MyEnum.Type type){
        return this.boardGameService.listByType(type);
    }

}
