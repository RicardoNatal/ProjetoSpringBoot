package br.com.godev.projeto.dto;

import br.com.godev.projeto.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginDTO {

    public String username;
    public String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
