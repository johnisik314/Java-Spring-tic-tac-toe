package com.johnprojects.tictactoe.service;

import com.johnprojects.tictactoe.exception.InvalidGameException;
import com.johnprojects.tictactoe.exception.InvalidParamException;
import com.johnprojects.tictactoe.exception.NotFoundException;
import com.johnprojects.tictactoe.model.Game;
import com.johnprojects.tictactoe.model.GamePlay;
import com.johnprojects.tictactoe.model.Player;
import com.johnprojects.tictactoe.model.Signs;
import com.johnprojects.tictactoe.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.johnprojects.tictactoe.model.GameStatus.*;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameID(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2,String gameID) throws InvalidParamException, InvalidGameException {
        if(!GameStorage.getInstance().getGames().containsKey(gameID)){
            throw new InvalidParamException("Game with provided ID doesnt exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameID);
        if (game.getPlayer2() !=null){
            throw new InvalidGameException("Game is not valid anymore");
        }
        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }
    public Game connectToRandomGame(Player player2) throws NotFoundException {
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(it->it.getStatus().equals(NEW))
                .findFirst().orElseThrow(()-> new NotFoundException("Game not found"));
        //find the game with new status

        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        //check if the game exists
        if(!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameID())) {
            throw new NotFoundException("Game not Found");
        }
        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameID());
        if(game.getStatus().equals(FINISHED)){
            throw new InvalidGameException("Game is already finished");
        }

        //enter the type value; X,O -> 0,1
        int [][] board = game.getBoard();
        board[gamePlay.getCordinateX()][gamePlay.getCordinateY()] = gamePlay.getType().getValue();

        checkWinner(game.getBoard(), Signs.X);
        checkWinner(game.getBoard(), Signs.O);

        return game;
    }
    private Boolean checkWinner(int [][]board, Signs sign){
    int [] boardArray = new int[9];
    int counterIndex = 0;
    for(int i=0; i<board.length;i++){
        for(int j = 0; j < board[i].length; j++){
            boardArray[counterIndex] = board[i][j];
            counterIndex++;
        }
    }
    int [][] winCombs = {{2,4,6},{0,4,8},{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8}};


    }
}
