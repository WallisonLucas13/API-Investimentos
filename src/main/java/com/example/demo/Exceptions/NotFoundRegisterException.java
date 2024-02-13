package com.example.demo.Exceptions;

import org.springframework.stereotype.Service;

@Service
public class NotFoundRegisterException extends RuntimeException{

    public NotFoundRegisterException() {
        super("Registro Inexistente!");
    }
}
