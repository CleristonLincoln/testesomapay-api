package br.com.somapay.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaldoException extends RuntimeException {
    public SaldoException(String message) {
        super(message);
    }
}
