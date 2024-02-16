package com.johnprojects.tictactoe.exception;

public class InvalidGameException extends Exception{
    private String messege;
    public InvalidGameException(String messege){
        this.messege = messege;
    }
    public String getMessege(){
        return messege;
    }
}
