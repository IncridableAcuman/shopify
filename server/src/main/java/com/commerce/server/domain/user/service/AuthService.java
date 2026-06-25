package com.commerce.server.domain.user.service;

import com.commerce.server.domain.token.service.TokenService;
import com.commerce.server.domain.token.util.CookieUtil;
import com.commerce.server.domain.token.util.JwtUtil;
import com.commerce.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;
    private final JwtUtil jwtUtil;
}
