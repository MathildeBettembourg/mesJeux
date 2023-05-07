package com.example.game.controller;

import com.example.game.entities.BoardGame;
import com.example.game.entities.Game;
import com.example.game.entities.Players;
import com.example.game.services.PlayersService;
import com.example.game.utils.BoardGameDTOOut;
import com.example.game.utils.GameDTOOut;
import com.example.game.utils.PlayersDTOOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private PlayersService playersService;

    //**many to many game and players
    @PostMapping("/players/{playerId}/games/{gameId}")
    public Players addGameToPlayer(@PathVariable Long playerId, @PathVariable Long gameId) {
        return playersService.addGameToPlayer(playerId, gameId);
    }
    @GetMapping("/player/{playerId}/playedGame")
    public List<GameDTOOut> listGamePlayed(@PathVariable Long playerId){
        return playersService.getListGamePlayed(playerId);
    }

    // Add a board game to a player's game list - many to many players board game
    @PostMapping("/players/{playerId}/boardgames/{boardGameId}")
    public Players addBoardGameToPlayer(@PathVariable Long playerId, @PathVariable Long boardGameId) {
        return playersService.addBoardGameToPlayer(playerId, boardGameId);
    }

    /**
     * findall o find all the player of the database
     *
     * @return a list of players
     */
    @GetMapping("")
    public List<PlayersDTOOut> findAll() {
        return playersService.findAll();
    }

    @GetMapping("/player/{id}/boardgames")
    public List<BoardGameDTOOut> listOfBordGamePlayed(@PathVariable Long id) {
        return playersService.findAllGameOfOnePlayer(id);
    }


    /**
     * find all to find a list of players by their ids
     *
     * @param longs list of ids
     * @return the list of corresponding players
     */
    @PostMapping("/findAList")
    public List<Players> findAllById(@RequestBody List<Long> longs) {
        return playersService.findAllById(longs);
    }

    /**
     * save to save in database a player
     *
     * @param entity as a Player
     * @return the player saved in database
     */
    @PostMapping("")
    public Players save(@RequestBody Players entity) {
        return playersService.save(entity);
    }

    /**
     * a function to find by id a player
     *
     * @param id as long
     * @return a player
     */
    @GetMapping("/{id}")
    public Players findById(@PathVariable Long id) {
        return playersService.findById(id);
    }

    /**
     * delete by id is a function to delete a player following its id
     *
     * @param id long the one of the player targeted
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        playersService.deleteById(id);
    }

    /**
     * findPlayerByFirstName to find a player easily by its firstname
     *
     * @param firstName as a string
     * @return the targeted player
     */
    @GetMapping("/firstName/{firstName}")
    public Players findPlayersByFirstName(@PathVariable String firstName) {
        return playersService.findPlayersByFirstName(firstName);
    }

    /**
     * findPlayerByLastName to find a player easily by its lastname
     *
     * @param lastName string
     * @return the targetedplayer
     */
    @GetMapping("/lastName/{lastName}")
    public Players findPlayersByLastName(@PathVariable String lastName) {
        return playersService.findPlayersByLastName(lastName);
    }

    @PostMapping("/{id}")
    public Players updatePlayer(@RequestBody Players players, @PathVariable Long id) {
        if (!id.equals(players.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "erreur de correspondence entre l'objets à modifier et son id en BDD");
        }
        return this.playersService.update(players);
    }

    @PostMapping("/winner/{playerId}/game/{gameId}")
    public Players addWinningEvent(@PathVariable Long playerId, @PathVariable Long gameId) {
        return this.playersService.addWinningEvent(playerId, gameId);
    }
    @GetMapping("/player/{id}/victories")
    public List<GameDTOOut> listOfGameWon(@PathVariable Long id) {
        return playersService.findAllGameWon(id);
    }
}
