package com.commerce.server.domain.token.service;

import com.commerce.server.domain.token.entity.TokenEntity;
import com.commerce.server.domain.token.repository.TokenRepository;
import com.commerce.server.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    @Transactional
    public void saveToken(UserEntity user,String  refreshToken){
        TokenEntity token = tokenRepository.findByUser(user).orElse(new TokenEntity());
        token.setUser(user);
        token.setRefreshToken(refreshToken);
        token.setExpiration(LocalDateTime.now().plusDays(7));
        tokenRepository.save(token);
    }
    @Transactional
    public void removeToken(UserEntity user){
        TokenEntity existToken = tokenRepository.findByUser(user).orElseThrow();
        tokenRepository.delete(existToken);
    }
}
