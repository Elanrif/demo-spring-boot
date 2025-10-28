package com.elanrif.inventory_management.security.services;

import com.elanrif.inventory_management.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenServiceImpl {
    public Optional<RefreshToken> findByToken(String token);
    public RefreshToken createRefreshToken(Long userId);
    public RefreshToken verifyExpiration(RefreshToken token);
    public int deleteByUserId(Long userId);
}
