package com.commerce.server.domain.user.service;

import com.commerce.server.domain.token.service.TokenService;
import com.commerce.server.domain.token.util.CookieUtil;
import com.commerce.server.domain.token.util.JwtUtil;
import com.commerce.server.domain.user.dto.*;
import com.commerce.server.domain.user.entity.UserEntity;
import com.commerce.server.domain.user.entity.enums.UserRole;
import com.commerce.server.domain.user.repository.UserRepository;
import com.commerce.server.exception.CustomBadRequestException;
import com.commerce.server.exception.CustomNotFoundException;
import com.commerce.server.util.mail.dto.EmailPayload;
import com.commerce.server.util.mail.producer.RabbitMQProducer;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RabbitMQProducer rabbitMQProducer;

    public AuthResponse register(RegisterRequest request, HttpServletResponse response){
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new CustomBadRequestException("User already exist");}
        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
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

    public void forgotPassword(ForgotPasswordRequest request){
        UserEntity user = findUserByEmail(request.getEmail());
        String token = jwtUtil.generateAccessToken(user);
        String url = "http://localhost:5173/reset-password?token="+token;
        EmailPayload payload = new EmailPayload(user.getEmail(),"Reset Password",url);
        rabbitMQProducer.sendMessageWithRabbitMQ(payload);
    }

    public void resetPassword(ResetPasswordRequest request){
        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new CustomBadRequestException("Password and confirm password do not mismatch");
        }
        if (!jwtUtil.validateToken(request.getToken())){
            throw new CustomBadRequestException("Invalid or expired token");
        }
        String email = jwtUtil.getSubjectFromToken(request.getToken());
        UserEntity user = findUserByEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        saveUser(user);
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
