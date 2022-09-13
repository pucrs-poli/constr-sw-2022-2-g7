package br.pucrs.auth.exceptions;

public class AuthBadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AuthBadRequestException(String message) {
        super(message);
    }
}
