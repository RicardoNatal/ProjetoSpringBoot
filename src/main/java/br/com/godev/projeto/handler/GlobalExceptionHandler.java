package br.com.godev.projeto.handler;

import br.com.godev.projeto.exception.TokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.godev.projeto.exception.PasswordException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NullPointerException.class, PasswordException.class, TokenException.class})
    public ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>("Falha interna no sistema: " + ex.getMessage(), HttpStatus.OK);
        } else if (ex instanceof PasswordException) {
            return new ResponseEntity<>("Senha incorreta: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        } else if (ex instanceof TokenException) {
            return new ResponseEntity<>("Token incorreto: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>("Houve um erro interno, desculpe o transtorno!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
