package br.com.godev.projeto.service;

import br.com.godev.projeto.exception.TokenException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoginService {

    public static List<String> tokens = new ArrayList<>();

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        tokens.add(token);
        return token;
    }

    public boolean isTokenValid(String newToken) {
        return tokens.contains(newToken);
    }
}
