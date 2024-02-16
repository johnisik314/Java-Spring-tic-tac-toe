package com.johnprojects.tictactoe.model;

import lombok.Data;

@Data
public class Game {
    private String gameID;
    private Player Player1;
    private Player Player2;
    private GameStatus status;
    private int [][] board;
    private Signs winner;
}