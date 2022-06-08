package br.com.squadra.bootcamp.desafiofinal.carolinedecamargo.addressregisterservice.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BusinessException extends ResponseStatusException {

    public BusinessException(String message, HttpStatus status) {
        super(status, message);
    }

    public BusinessException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
