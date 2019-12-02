package xyz.crowxx.imserver.service;

import org.springframework.stereotype.Service;
import xyz.crowxx.imserver.model.Token;
import xyz.crowxx.imserver.repository.TokenRepository;

import javax.annotation.Resource;

@Service
public class TokenService {
    @Resource
    TokenRepository tokenRepository;
    public Token findTokenByToken(String token){
        return tokenRepository.findTokenByToken(token);
    }

    public Token addToken(Token token){
        return tokenRepository.save(token);
    }
}
