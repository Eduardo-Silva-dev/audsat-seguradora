package br.com.audsat.seguradora.exception;

public class AlreadyExistingException extends RuntimeException {
    public AlreadyExistingException(String mensagem) {
        super(mensagem);
    }
}