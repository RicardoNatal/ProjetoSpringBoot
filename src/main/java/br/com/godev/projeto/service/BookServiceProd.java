package br.com.godev.projeto.service;

import br.com.godev.projeto.dto.LoginDTO;
import br.com.godev.projeto.exception.PasswordException;
import br.com.godev.projeto.exception.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class BookServiceProd implements BookProfileService{

    @Autowired
    private LoginService loginService;

    @Override
    public boolean isValidToken(String token) {
        return loginService.isTokenValid(token);
    }
}
