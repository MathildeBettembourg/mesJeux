package com.example.game.services;


import com.example.game.entities.Game;
import com.example.game.entities.Players;
import com.example.game.repositories.GameRepository;
import com.example.game.utils.GameDTOOut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final PlayersService playerService;
    private final BoardGameService boardGameService;

    public GameServiceImpl(GameRepository gameRepository, PlayersService playerService, BoardGameService boardGameService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.boardGameService = boardGameService;
    }

    @Override
    public Game addPlayerToGame(Long gameId, List<Integer> playersId) {
        Game game = gameRepository.findById(gameId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        for(int i = 0; i<playersId.size(); i++){
            Players  playerToAdd = playerService.findById(playersId.get(i).longValue());
            playerToAdd.getGames().add(game);
            if(game.getNumberOfPlayers()==null){
                game.setNumberOfPlayers(1);
            }else {
                game.setNumberOfPlayers(game.getNumberOfPlayers() + 1);
            };
            this.playerService.save(playerToAdd);
            this.gameRepository.save(game);
        };
        return game;
    }

    @Override
    public List<GameDTOOut> findAll() {
        List<Game> listOut = gameRepository.findAll();
        List<GameDTOOut> listForView = new ArrayList<>();
        for(int i =0; i<listOut.size();i++){
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
        return gameRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
