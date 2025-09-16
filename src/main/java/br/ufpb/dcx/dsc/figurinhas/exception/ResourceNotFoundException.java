package br.ufpb.dcx.dsc.figurinhas.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Object id) {
        super(resource + " with id '" + id + "' was not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

