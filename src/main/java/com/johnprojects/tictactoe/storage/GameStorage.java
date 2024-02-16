package com.johnprojects.tictactoe.storage;

import com.johnprojects.tictactoe.model.Game;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class GameStorage {

    @Getter
    private static Map<String, Game> games;
    private static GameStorage instance;

    private GameStorage(){
        games = new HashMap<>();
    }

    public static synchronized GameStorage getInstance(){
        if(instance==null){
            instance = new GameStorage();
        }
        return  instance;
    }

    public void setGame(Game game){
        games.put(game.getGameID(), game);
    }
}
