package br.com.godev.projeto;

import br.com.godev.projeto.dto.LoginDTO;
import br.com.godev.projeto.service.BookProfileService;
import br.com.godev.projeto.service.LoginService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import br.com.godev.projeto.dto.LoginDTO;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    @Autowired
    private LoginService loginService;

    @Value("${spring.profiles.active}")
    String profile;

    @Autowired
    private BookProfileService bookProfileService;

    @Override
    public void doFilter(ServletRequest servletRequest, //
                         ServletResponse servletResponse, //
                         FilterChain filterChain) //
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String authorization = request.getHeader("authorization");
        final String uri = request.getRequestURI();

//        if (uri.equals("/login") || profile.equals("dev") )

        if (uri.equals("/login") || bookProfileService.isValidToken(authorization)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
        }

    }
}