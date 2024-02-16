package com.johnprojects.tictactoe.exception;

public class NotFoundException extends Exception{
    private String messege;
    public NotFoundException(String messege){
        this.messege = messege;
    }
    public String getMessege(){
        return messege;
    }
}
