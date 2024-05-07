package br.com.audsat.seguradora.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTests {

    @Test
    public void testNotFoundException() {
        String mensagem = "Recurso não encontrado.";
        NotFoundException exception = new NotFoundException(mensagem);
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    public void testAlreadyExistingException() {
        String mensagem = "Recurso já existe.";
        AlreadyExistingException exception = new AlreadyExistingException(mensagem);
        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    public void testHandleNotFoundException() {
        String mensagem = "Recurso não encontrado.";
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<String> responseEntity = handler.handleNotFoundException(new NotFoundException(mensagem));
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(mensagem, responseEntity.getBody());
    }

    @Test
    public void testHandleAlreadyExistingException() {
        String mensagem = "Recurso já existe.";
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<String> responseEntity = handler.handleAlreadyExistingException(new AlreadyExistingException(mensagem));
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals(mensagem, responseEntity.getBody());
    }
}
