package com.example.game.controller;

import com.example.game.entities.Game;
import com.example.game.services.GameServiceImpl;
import com.example.game.utils.GameDTOOut;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameServiceImpl gameService;

    public GameController(GameServiceImpl gameService) {
        this.gameService = gameService;
    }


    //**many to many game and players
    @PostMapping("/games/{gameId}/players")
    public Game addPlayersToGame(@PathVariable Long gameId, @RequestBody List<Integer> playersId) {
        return this.gameService.addPlayerToGame(gameId, playersId);
    }

    @GetMapping("")
    public List<GameDTOOut> findAll() {
        return gameService.findAll();
    }

    @PostMapping("")
    public Game save(@RequestBody Game game) {
        return gameService.save(game);
    }

    @GetMapping("/{id}")
    public Game findById(@PathVariable Long id) {
        return gameService.findById(id);
    }

    public boolean existsById(Long id) {
        return gameService.existsById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        gameService.deleteById(id);
    }

    //one to may game and board games
//    @PostMapping("/games/{gameId}/boardgames/{boardGameId}")
//    public ResponseEntity<?> addBoardGameToGame(@PathVariable Long gameId, @PathVariable Long boardGameId) {
//        gameService.addBoardGameToGame(boardGameId, gameId);
//        return ResponseEntity.ok().build();
//    }
}
