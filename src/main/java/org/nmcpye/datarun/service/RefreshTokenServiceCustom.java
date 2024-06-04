package org.nmcpye.datarun.service;

import org.nmcpye.datarun.domain.RefreshToken;
import org.nmcpye.datarun.domain.User;

import java.util.Optional;

/**
 * Service Interface for managing {@link RefreshToken}.
 */
public interface RefreshTokenServiceCustom extends RefreshTokenService {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUserLogin(String login);
}
