package br.ufpb.dcx.dsc.figurinhas.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

