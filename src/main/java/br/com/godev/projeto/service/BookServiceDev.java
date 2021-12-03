package br.com.godev.projeto.service;

import br.com.godev.projeto.dto.LoginDTO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class BookServiceDev implements BookProfileService{

    @Override
    public boolean isValidToken(String token) {
        return true;
    }
}
