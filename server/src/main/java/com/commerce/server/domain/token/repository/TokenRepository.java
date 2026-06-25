package com.commerce.server.domain.token.repository;

import com.commerce.server.domain.token.entity.TokenEntity;
import com.commerce.server.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity,Long> {
    Optional<TokenEntity> findByUser(UserEntity user);
}
