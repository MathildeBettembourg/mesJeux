package com.example.game.services;

import com.example.game.entities.BoardGame;
import com.example.game.entities.Game;
import com.example.game.entities.Players;
import com.example.game.repositories.BoardGameRepository;
import com.example.game.repositories.GameRepository;
import com.example.game.repositories.PlayersRepository;
import com.example.game.utils.BoardGameDTOOut;
import com.example.game.utils.MyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardGameServiceImpl implements BoardGameService {
    private final BoardGameRepository boardGameRepository;
    private static final Logger logger = LoggerFactory.getLogger(BoardGameServiceImpl.class);

    private Players players;


    public BoardGameServiceImpl(BoardGameRepository boardGameRepository) {
        this.boardGameRepository = boardGameRepository;
    }

    @Override
    public List<BoardGameDTOOut> findAll() {
        List<BoardGame> listOut = boardGameRepository.findAll();
        List<BoardGameDTOOut> listForView = new ArrayList<>();
        if(listOut.size()!=0){
            for(int i =0; i< listOut.size();i++){
                BoardGameDTOOut boardGameDTOOut = new BoardGameDTOOut(listOut.get(i).getId(), listOut.get(i).getName());
                listForView.add(boardGameDTOOut);
            }}else{
            logger.warn("nothing to display");
        }
        return listForView;
    }


    @Override
    public List<BoardGameDTOOut> findAllById(Iterable<Long> longs) {
        List<BoardGame> listOut = boardGameRepository.findAllById(longs);
        List<BoardGameDTOOut> listForViewFinByAll = new ArrayList<>();
        if(listOut.size()>1){
            for(int i = 0; i<listOut.size();i++){
                BoardGameDTOOut boardGameDTOOut = new BoardGameDTOOut(listOut.get(i).getId(), listOut.get(i).getName());
                listForViewFinByAll.add(boardGameDTOOut);
            }
        }else{
            logger.warn("nothing to display");
        };
        return listForViewFinByAll;
    }

    @Override
    public BoardGame save(BoardGame entity) {
        return boardGameRepository.save(entity);
    }

    @Override
    public void addPlayerToGame(Long playerId, Long gameId) {

    }
    @Override
    public BoardGame findById(Long id) {
        BoardGame boardGame  = boardGameRepository.findById(id).orElseThrow(() -> {
            logger.warn("tentative de recuperation d'une entiteavec un id erroné");
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        return  boardGame;
    }

    @Override
    public boolean existsById(Long id) {
        return boardGameRepository.existsById(id);
    }

    @Override
    public long count() {
        return boardGameRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        boardGameRepository.deleteById(id);
    }
    @Override
    public BoardGame update(BoardGame boardGame){
        if (!this.boardGameRepository.existsById(boardGame.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "introuvable");
        }
        return this.boardGameRepository.save(boardGame);
    }

//    /**
//     * Find all to find all the boarg game
//     * @param sort array of boardgames
//     * @return
//     */
//    public List<BoardGame> findAll(Sort sort) {
//        return boardGameRepository.findAll(sort);
//    }

    @Override
    public List<BoardGameDTOOut> findByNumberOfPlayersMin(Integer numberOfPlayerMin) {
        List<BoardGame> listOut = boardGameRepository.findByNbOfPlayersMinIsLessThanEqual(numberOfPlayerMin);
        List<BoardGameDTOOut> listForView = new ArrayList<>();
        for(int i =0; i< listOut.size();i++){
            BoardGameDTOOut boardGameDTOOut = new BoardGameDTOOut(listOut.get(i).getId(), listOut.get(i).getName());
            listForView.add(boardGameDTOOut);
        }
        return listForView;
    }

    @Override
    public List<BoardGameDTOOut> findByNumberOfPlayersMax(Integer numberOfPlayerMax){
        List<BoardGame> listOut = boardGameRepository.findByNbOfPlayersMaxIsLessThanEqual(numberOfPlayerMax);
        List<BoardGameDTOOut> listForView = new ArrayList<>();
        if(listOut.size()!=0){
            for(int i =0; i< listOut.size();i++) {
                BoardGameDTOOut boardGameDTOOut = new BoardGameDTOOut(listOut.get(i).getId(), listOut.get(i).getName());
                listForView.add(boardGameDTOOut);
            }}else{
            logger.warn("nothing foung");
        }
        return listForView;

    };

    @Override
    public List<BoardGameDTOOut> findByTime(Integer time){
        List<BoardGame> listOut = boardGameRepository.findByTime(time);
        List<BoardGameDTOOut> listForView = new ArrayList<>();
        for(int i =0; i<= listOut.size();i++){
            BoardGameDTOOut boardGameDTOOut = new BoardGameDTOOut(listOut.get(i).getId(), listOut.get(i).getName());
            listForView.add(boardGameDTOOut);
        }
        return listForView;
    };

    @Override
    public List<BoardGameDTOOut> findByExperience(MyEnum.Experience experience){
        List<BoardGame> listOut = boardGameRepository.findByExperience(experience);
        List<BoardGameDTOOut> listForView = new ArrayList<>();
        for(int i =0; i< listOut.size();i++){
            BoardGameDTOOut boardGameDTOOut = new BoardGameDTOOut(listOut.get(i).getId(), listOut.get(i).getName());
            listForView.add(boardGameDTOOut);
        }
        return listForView;
    };
    //**a enlever, seul le jeu doit le faire
//    @Override
//    public BoardGame addGameIdToBoardGameHistoric(Long gameId, Long boardGameId) {
//        Game game = gameRepository.findById(gameId).orElseThrow();
//        BoardGame boardGame = boardGameRepository.findById(boardGameId).orElseThrow();
//        boardGame.getListOfgamesOfThatBoardGamePlayed().add(game);
//        return this.boardGameRepository.save(boardGame);
//    }
    //**a enlever, seul le jeu doit le faire

//    public BoardGame addPlayerThatPlayerToThatBoardGame(Long playerId, Long boardgameId) {
//        Players player = playersRepository.findById(playerId).orElseThrow();
//        BoardGame boardgame = boardGameRepository.findById(boardgameId).orElseThrow();
//        boardgame.getPlayersList().add(player);
//        return boardGameRepository.save(boardgame);
//    }

    @Override
    public List<BoardGameDTOOut> listByType(MyEnum.Type type) {
        List<BoardGame> listOut = boardGameRepository.findBoardGameByType(type);
        List<BoardGameDTOOut> listForView = new ArrayList<>();
        for(int i =0; i< listOut.size();i++){
            BoardGameDTOOut boardGameDTOOut = new BoardGameDTOOut(listOut.get(i).getId(), listOut.get(i).getName());
            listForView.add(boardGameDTOOut);
        }
        return listForView;
    };

}
