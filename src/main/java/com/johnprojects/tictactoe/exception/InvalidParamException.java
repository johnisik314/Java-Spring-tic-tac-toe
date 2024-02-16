package com.johnprojects.tictactoe.exception;

public class InvalidParamException extends Exception{
    private String messege;
    public InvalidParamException(String messege){
        this.messege = messege;
    }
    public String getMessege(){
        return messege;
    }
}
