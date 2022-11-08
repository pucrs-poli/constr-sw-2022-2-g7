package br.pucrs.classesms.exception;

public class ClassNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ClassNotFoundException(String message) {
        super(message);
    }
}
