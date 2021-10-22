package br.com.somapay.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntidadeNaoEncontradaException extends NegocioException{

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
