package io.github.shriohoo.ecommerce.user.adapter.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.shriohoo.ecommerce.user.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Environment environment;
    private final UserService userService;
    private final ObjectMapper objectMapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager, Environment environment, UserService userService, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.environment = environment;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin credential = objectMapper.readValue(request.getInputStream(), RequestLogin.class);
            return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(
                    credential.getEmail(),
                    credential.getPassword(),
                    new ArrayList<>()
                ));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String expirationTime = environment.getProperty("token.expiration_time");
        String secretKey = environment.getProperty("token.secret");
        String token = Jwts.builder()
            .setSubject(((User) authResult.getPrincipal()).getUsername())
            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expirationTime)))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact();
        response.addHeader("Authorization", token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("unsuccessful");
        super.unsuccessfulAuthentication(request, response, failed);
    }

}
