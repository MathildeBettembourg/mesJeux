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

    @GetMapping("/player/{playerId}/playedGame")
    public List<GameDTOOut> listGamePlayed(@PathVariable Long playerId){
        return playersService.getListGamePlayed(playerId);
    }

    /**
     * findall o find all the player of the database
     *
     * @return a list of players+
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
     * save to save in database a player
     *
     * @param entity as a Player
     * @return the player saved in database+
     */
    @PostMapping("")
    public Players save(@RequestBody Players entity) {
        return playersService.save(entity);
    }

    /**
     * a function to find by id a player
     *
     * @param id as long+
     * @return a player
     */
    @GetMapping("/{id}")
    public Players findById(@PathVariable Long id) {
        return playersService.findById(id);
    }

    /**
     * delete by id is a function to delete a player following its id
     * @param id long the one of the player targeted+
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        playersService.deleteById(id);
    }

    /**
     * findPlayerByFirstName to find a player easily by its firstname
     * @param firstName as a string
     * @return the targeted player+
     */
    @GetMapping("/firstName/{firstName}")
    public List<Players> findPlayersByFirstName(@PathVariable String firstName) {
        return playersService.findPlayersByFirstName(firstName);
    }

    /**
     * findPlayerByLastName to find a player easily by its lastname+
     * @param lastName string
     * @return the targetedplayer+
     */
    @GetMapping("/lastName/{lastName}")
    public List<Players> findPlayersByLastName(@PathVariable String lastName) {
        return playersService.findPlayersByLastName(lastName);
    }

    /**
     * to check game that were won by a player
     * @param id of the player
     * @return the game won by a player
     */
    @GetMapping("/player/{id}/victories")
    public List<GameDTOOut> listOfGameWon(@PathVariable Long id) {
        return playersService.findAllGameWon(id);
    }
}
