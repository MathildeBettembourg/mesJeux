package com.example.game.services;


import com.example.game.entities.BoardGame;
import com.example.game.entities.Game;
import com.example.game.entities.Players;
import com.example.game.repositories.GameRepository;
import com.example.game.utils.GameDTOOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final PlayersService playerService;
    private final BoardGameService boardGameService;
    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    public GameServiceImpl(GameRepository gameRepository, PlayersService playerService, BoardGameService boardGameService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.boardGameService = boardGameService;
    }

    /**
     * Add winner is made to add a winner, it add the winner to the game,
     * the game to the player list of game won and increment the number of game won by a player
     * @param gameId Long
     * @param playerId Long
     * @return the Player
     */
    public Game addWinner(Long gameId, Long playerId) {
        Game game = this.findById(gameId);
        Players players=this.playerService.findById(playerId);
        if(players.getNumberOfGameWon()==null){
            players.setNumberOfGameWon(0);
        }else{
        players.setNumberOfGameWon(players.getNumberOfGameWon()+1);
        }
        players.getGameWon().add(game);
        this.playerService.save(players);
        game.setWinner(players);
        return this.save(game);
    }

    @Override
    @Transactional
    public Game addPlayerToGame(Long gameId, Iterable<Long> playersId) {
        logger.warn(playersId.toString());
        List<Long> listPlayer =new ArrayList<>();
        for (Long players: playersId
             ) {listPlayer.add(players);

        }
        List<Players> listPlayers = this.playerService.findAllById(listPlayer);
        Game game = this.findById(gameId);
        for (int i = 0; i < listPlayers.size(); i++) {
            listPlayers.get(i).getGames().add(game);
            if (game.getNumberOfPlayers() == null) {
                game.setNumberOfPlayers(1);
            } else {
                game.setNumberOfPlayers(game.getNumberOfPlayers() + 1);
            }
            ;
            this.playerService.save(listPlayers.get(i));
        }
        return this.gameRepository.save(game);
    }

    /**
     * This function is used when a new game start
     * it add a boardgame to the game and add a game to the boadgame cards
     * @param gameId Long
     * @param boardgameId Long
     * @return the Game
     */
    @Override
    public Game addBoardGameToTheGame(Long gameId, Long boardgameId) {
        Game game = this.findById(gameId);
        BoardGame boardGame = this.boardGameService.findById(boardgameId);
        boardGame.getListOfgamesOfThatBoardGamePlayed().add(game);
        game.setBoardGamePlayed(boardGame);
        return this.save(game);
    }

    @Override
    public List<GameDTOOut> findAll() {
        List<Game> listOut = gameRepository.findAll();
        List<GameDTOOut> listForView = new ArrayList<>();
        for (int i = 0; i < listOut.size(); i++) {
            GameDTOOut gameDTOOut = new GameDTOOut(listOut.get(i).getId(), listOut.get(i).getDateOfGame());
            listForView.add(gameDTOOut);
        }
        return listForView;
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game findById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public boolean existsById(Long id) {
        return gameRepository.existsById(id);
    }

    @Override
    public void deleteById(Long aLong) {
        gameRepository.deleteById(aLong);
    }

}
