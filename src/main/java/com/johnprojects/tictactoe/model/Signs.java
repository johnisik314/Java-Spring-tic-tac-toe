package com.johnprojects.tictactoe.model;

import lombok.Getter;
import  lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum Signs{
    X(1),O(2);

    private Integer value;

}