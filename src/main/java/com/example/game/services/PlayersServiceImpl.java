package com.example.game.services;

import com.example.game.entities.BoardGame;
import com.example.game.entities.Game;
import com.example.game.entities.Players;
import com.example.game.repositories.BoardGameRepository;
import com.example.game.repositories.GameRepository;
import com.example.game.repositories.PlayersRepository;
import com.example.game.utils.BoardGameDTOOut;
import com.example.game.utils.GameDTOOut;
import com.example.game.utils.PlayersDTOOut;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayersServiceImpl implements PlayersService {
    private final PlayersRepository playersRepository;


    private static final Logger logger = LoggerFactory.getLogger(PlayersServiceImpl.class);

    public PlayersServiceImpl(PlayersRepository playersRepository, GameRepository gameRepository, BoardGameRepository boardGameRepository) {
        this.playersRepository = playersRepository;
    }

    public List<Players> findAllById(Iterable<Long> longs) {
        return playersRepository.findAllById(longs);
    }

    //
//    public Players addWinningEvent(Long playerId, Long gameId) {
//        Players player = playersRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerId));
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("error not found"));
//        player.getGameWon().add(game);
//        if(player.getNumberOfGameWon()==null){
//            player.setNumberOfGameWon(1);
//        }else {
//            player.setNumberOfGameWon(player.getNumberOfGameWon() + 1);
//        }
//        game.setWinner(player);
//        gameRepository.save(game);
//        return playersRepository.save(player);
//    }
////id
//    public Players addGameToPlayer(Long playerId, Long gameId) {
//        Players player = playersRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerId));
//        Game games = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("not found"));
//        player.getGames().add(games);
//        if(player.getNumberOfGamePlayed()==null){
//            player.setNumberOfGamePlayed(1);
//        }else{
//            player.setNumberOfGamePlayed(player.getNumberOfGamePlayed()+1);
//        }
//        player.setNumberOfGamePlayed(player.getNumberOfGamePlayed() + 1);
//        return playersRepository.save(player);
//
//    }
////id
//    public Players addBoardGameToPlayer(Long playerId, Long boardGameId) {
//        Players player = playersRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerId));
//        BoardGame boardGames = boardGameRepository.findById(boardGameId).orElseThrow(() -> new EntityNotFoundException("P")) ;
//        player.getListBoardGame().add(boardGames);
//        boardGames.getPlayersList().add(player);
//        this.boardGameRepository.save(boardGames);
//        return playersRepository.save(player);
//    }
public List<GameDTOOut> getListGamePlayed(Long playerId){
    List<GameDTOOut> listToSend =new ArrayList<>();
    Players player = playersRepository.findById(playerId).orElseThrow(() -> new EntityNotFoundException("Player not found with id: " + playerId));
    if(player.getGames().size()==0){
        logger.warn("no game");
    }else{
        for(int i =0;i<player.getGames().size();i++){
            listToSend.add(new GameDTOOut(player.getGames().get(i).getId(),player.getGames().get(i).getDateOfGame() ));
        }
    }
    return listToSend;
}
    @Override
    public List<PlayersDTOOut> findAll() {
        List<Players> listOut = playersRepository.findAll();
        List<PlayersDTOOut> listForView = new ArrayList<>();
        for(int i =0; i<listOut.size();i++){
            PlayersDTOOut boardGameDTOOut = new PlayersDTOOut(listOut.get(i).getId(), listOut.get(i).getFirstName(), listOut.get(i).getLastName());
            listForView.add(boardGameDTOOut);
        }
        return listForView;
    }

    @Override
    public List<Players> findAllById(List<Long> longs) {
        return playersRepository.findAllById(longs);
    }

    @Override
    public Players save(Players entity) {
        return playersRepository.save(entity);
    }

    @Override
    public Players findById(Long id) {
        return playersRepository.findById(id).orElseThrow(() -> {
            logger.warn("tentative de recuperation d'une entiteavec un id erroné");
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }
    public List<BoardGameDTOOut> findAllGameOfOnePlayer(Long id){
        Players playerToTarget = this.findById(id);
        List<BoardGameDTOOut> listGamedPlayedDto = new ArrayList<>();
        if(playerToTarget.getListBoardGame().size()>=1){
        for(int i =0; i<playerToTarget.getListBoardGame().size();i++){
        listGamedPlayedDto.add(new BoardGameDTOOut(playerToTarget.getListBoardGame().get(i).getId(), playerToTarget.getListBoardGame().get(i).getName()));
        }
        }else{logger.warn("no boardgametodisplay");
        }
            return listGamedPlayedDto;
}
    public List<GameDTOOut>findAllGameWon(Long id){
        Players playerToTarget = this.findById(id);
        List<GameDTOOut> listGamedWonDto = new ArrayList<>();
        if(playerToTarget.getGameWon().size()>=1){
            for(int i =0; i<playerToTarget.getGameWon().size();i++){
                listGamedWonDto.add(new GameDTOOut(playerToTarget.getGameWon().get(i).getId(), playerToTarget.getGameWon().get(i).getDateOfGame()));
            }
        }else{logger.warn("no boardgametodisplay");
        }
        return listGamedWonDto;
    }

    ;

    @Override
    public boolean existsById(Long id) {
        return playersRepository.existsById(id);
    }

    @Override
    public long count() {
        return playersRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        playersRepository.deleteById(id);
    }

    @Override
    public List<Players> findPlayersByFirstName(String firstName) {
        return playersRepository.findPlayersByFirstName(firstName);
    }

    ;

    @Override
    public List<Players> findPlayersByLastName(String lastName) {
        return playersRepository.findPlayersByLastName(lastName);
    }

    ;

    @Override
    public Players update(Players players) {
        if (!this.playersRepository.existsById(players.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "introuvable");
        }
        return this.playersRepository.save(players);
    }
}
