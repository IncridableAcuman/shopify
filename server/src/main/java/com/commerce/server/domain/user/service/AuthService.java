package com.commerce.server.domain.user.service;

import com.commerce.server.domain.token.service.TokenService;
import com.commerce.server.domain.token.util.CookieUtil;
import com.commerce.server.domain.token.util.JwtUtil;
import com.commerce.server.domain.user.dto.AuthResponse;
import com.commerce.server.domain.user.dto.LoginRequest;
import com.commerce.server.domain.user.dto.RegisterRequest;
import com.commerce.server.domain.user.entity.UserEntity;
import com.commerce.server.domain.user.entity.enums.UserRole;
import com.commerce.server.domain.user.repository.UserRepository;
import com.commerce.server.exception.CustomBadRequestException;
import com.commerce.server.exception.CustomNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new CustomBadRequestException("User already exist");}
        UserEntity user = new UserEntity();
        user.setUsername(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.USER);
        saveUser(user);
        return authResponse(user,response);
    }
    public AuthResponse login(LoginRequest request,HttpServletResponse response){
        UserEntity user = findUserByEmail(request.getEmail());
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new CustomBadRequestException("Password mismatch");}
        return authResponse(user,response);
    }
    public AuthResponse refresh(String refreshToken,HttpServletResponse response){
        if ( refreshToken==null || !jwtUtil.validateToken(refreshToken)){
            throw new CustomBadRequestException("Invalid token");}
        String email = jwtUtil.getSubjectFromToken(refreshToken);
        UserEntity user = findUserByEmail(email);
        return authResponse(user,response);
    }
    public void logout(String refreshToken,HttpServletResponse response){
        if ( refreshToken==null || !jwtUtil.validateToken(refreshToken)){
            throw new CustomBadRequestException("Invalid token");}
        String email = jwtUtil.getSubjectFromToken(refreshToken);
        UserEntity user = findUserByEmail(email);
        tokenService.removeToken(user);
        cookieUtil.clearCookie(response);
    }

    public AuthResponse authResponse(UserEntity user,HttpServletResponse response){
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        tokenService.saveToken(user,refreshToken);
        cookieUtil.addCookie(refreshToken,response);
        return AuthResponse.from(accessToken);
    }
    public UserEntity findUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new CustomNotFoundException("User not found"));
    }

    @Transactional
    public void saveUser(UserEntity user){
        userRepository.save(user);
    }
}
