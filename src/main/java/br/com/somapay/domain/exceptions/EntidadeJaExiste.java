package br.com.somapay.domain.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntidadeJaExiste extends NegocioException{

    public EntidadeJaExiste(String message) {
        super(message);
    }

}
