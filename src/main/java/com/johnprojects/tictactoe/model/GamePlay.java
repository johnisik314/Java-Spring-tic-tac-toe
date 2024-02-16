package com.johnprojects.tictactoe.model;

import lombok.Data;

@Data
public class GamePlay {
    private Signs type;
    private Integer cordinateX;
    private Integer cordinateY;
    private String gameID;

}
