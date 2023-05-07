package com.example.game.services;

import org.springframework.stereotype.Service;

@Service
public class GameServiceImplOne {
//        private final GameRepository gameRepository;
//        private final PlayersRepository playerRepository;
//        private final BoardGameRepository boardGameRepository;
//
//
//    public GameServiceImpl(GameRepository gameRepository, PlayersRepository playerRepository, BoardGameRepository boardGameRepository) {
//        this.gameRepository = gameRepository;
//        this.playerRepository = playerRepository;
//        this.boardGameRepository = boardGameRepository;
//    }
//
//
//    public Game addPlayerToGame(Long gameId, List<Integer> playersId) {
//        Game game = gameRepository.findById(gameId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
//        for(int i = 0; i<playersId.size(); i++){
//            Players  playerToAdd = playerRepository.findById(playersId.get(i).longValue()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
//                playerToAdd.getGames().add(game);
//                if(game.getNumberOfPlayers()==null){
//                    game.setNumberOfPlayers(1);
//                }else {
//                    game.setNumberOfPlayers(game.getNumberOfPlayers() + 1);
//                };
//                this.playerRepository.save(playerToAdd);
//                this.gameRepository.save(game);
//        };
//        return game;
//    }
//
//    public List<GameDTOOut> findAll() {
//        List<Game> listOut = gameRepository.findAll();
//        List<GameDTOOut> listForView = new ArrayList<>();
//        for(int i =0; i<listOut.size();i++){
//            GameDTOOut gameDTOOut = new GameDTOOut(listOut.get(i).getId(), listOut.get(i).getDateOfGame());
//            listForView.add(gameDTOOut);
//        }
//        return listForView;
//    }
//
//
//    public Game save(Game game) {
//        return gameRepository.save(game);
//    }
//
//    public Game findById(Long id) {
//        return gameRepository.findById(id).orElseThrow(()-> new RuntimeException());
//    }
//
//    public boolean existsById(Long id) {
//        return gameRepository.existsById(id);
//    }
//
//    public void deleteById(Long aLong) {
//        gameRepository.deleteById(aLong);
//    }
}
