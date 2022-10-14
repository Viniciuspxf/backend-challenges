package br.com.picpay.account_manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RequestFailedException extends ResponseStatusException {

    public RequestFailedException(String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
