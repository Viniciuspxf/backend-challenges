package br.com.picpay.account_manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidTransactionException extends ResponseStatusException {

    public InvalidTransactionException(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
