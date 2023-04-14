package com.api.cep.exceptions;

public class EnderecoNaoEncontradoException extends RuntimeException{

    public EnderecoNaoEncontradoException(String message){
        super(message);
    }
}
