package com.example.game.controller;

import com.example.game.entities.Game;
import com.example.game.services.GameServiceImpl;
import com.example.game.utils.GameDTOOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameServiceImpl gameService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }


    /**
     * add players to a game
     * @param gameId the game to edit
     * @param playersId the list of player as string
     * @return the game
     */
    @PostMapping("/games/{gameId}/players/{playersId}")
    public Game addPlayersToGame(@PathVariable Long gameId, @PathVariable String playersId) {
        List<Long> playersIdList = Arrays.asList(playersId.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
        logger.warn(playersId.toString());
        return this.gameService.addPlayerToGame(gameId, playersIdList);
    }

    /**
     * to add a board game to the game
     * @param gameId long
     * @param boardgameId long
     * @return the game-
     */
    @PostMapping("/games/{gameId}/boardGame/{boardgameId}")
    public Game addBoardGameToTheGame(@PathVariable Long gameId, @PathVariable Long boardgameId){
        return  this.gameService.addBoardGameToTheGame(gameId, boardgameId);
    }

    /**
     * to add a winner to a game
     * @param gameId gameid long
     * @param playerId longplayer id
     * @return the game-
     */
    @PostMapping("/{gameId}/winner/{playerId}")
    public Game addWinner(@PathVariable Long gameId, @PathVariable Long playerId){
        return this.gameService.addWinner(gameId, playerId);
    }

    /**
     * to find all the games
     * @return list of game id plus date-
     */
    @GetMapping("")
    public List<GameDTOOut> findAll() {
        return gameService.findAll();
    }

    /**
     * save a new game
     * @param game Game
     * @return the new game-
     */
    @PostMapping("")
    public Game save(@RequestBody Game game) {
        return gameService.save(game);
    }

    /**
     * find a game by its id
     * @param id long
     * @return the game-
     */
    @GetMapping("/{id}")
    public Game findById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    /**
     * delete a game by its id
     * @param id long of the game -
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        gameService.deleteById(id);
    }

}
