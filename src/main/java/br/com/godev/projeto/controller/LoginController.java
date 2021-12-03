package br.com.godev.projeto.controller;

import br.com.godev.projeto.dto.LoginDTO;
import br.com.godev.projeto.exception.PasswordException;
import br.com.godev.projeto.exception.TokenException;
import br.com.godev.projeto.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginDTO loginLocal = new LoginDTO("mario", "admin");

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO login) {

        if (login.username.equals(loginLocal.username) && login.password.equals(loginLocal.password)) {
            String token = loginService.generateToken();
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            throw new PasswordException();
        }
    }
}

