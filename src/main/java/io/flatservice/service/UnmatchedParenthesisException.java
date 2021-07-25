package io.flatservice.service;

public class UnmatchedParenthesisException extends RuntimeException{
    public UnmatchedParenthesisException(){
        super("Unmatched parenthesis");
    }
}
