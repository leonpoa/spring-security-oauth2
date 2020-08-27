package com.code.demo.service;

import com.code.demo.domain.Token;
import com.code.demo.domain.enums.TokenType;
import com.code.demo.exception.InvalidTokenException;
import com.code.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    private static final String TOKEN_NOT_FOUND = "Token %s not found";

    @Autowired
    private TokenRepository repository;

    public String createActivationToken(String username) {
        Token activationToken = new Token();
        activationToken.setId(UUID.randomUUID().toString());
        activationToken.setUserId(username);
        activationToken.setType(TokenType.ACTIVATION);
        activationToken.setExpiration(null);
        repository.save(activationToken);
        return activationToken.getId();
    }

    public Token findActivationToken(String token) {
        return repository.findByIdAndType(token, TokenType.ACTIVATION).orElseThrow(() ->
                new InvalidTokenException(String.format(TOKEN_NOT_FOUND, token)));
    }

    public void remove(Token token) {
        repository.delete(token);
    }

}
