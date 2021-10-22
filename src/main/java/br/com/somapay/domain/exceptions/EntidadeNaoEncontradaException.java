package br.com.somapay.domain.exceptions;

public class EntidadeNaoEncontradaException extends NegocioException{

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
