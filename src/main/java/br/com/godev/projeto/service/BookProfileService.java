package br.com.godev.projeto.service;


import br.com.godev.projeto.dto.LoginDTO;

public interface BookProfileService {

    boolean isValidToken(String token);
}
