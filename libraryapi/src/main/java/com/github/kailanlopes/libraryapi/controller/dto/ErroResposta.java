package com.github.kailanlopes.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.logging.Handler;

public record ErroResposta(Integer status, String mensagem, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String mensagem){
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

}
