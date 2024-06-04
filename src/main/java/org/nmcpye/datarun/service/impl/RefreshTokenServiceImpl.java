package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.RefreshToken;
import org.nmcpye.datarun.repository.RefreshTokenRepository;
import org.nmcpye.datarun.service.RefreshTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.RefreshToken}.
 */
@Service
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final Logger log = LoggerFactory.getLogger(RefreshTokenServiceImpl.class);

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        log.debug("Request to save RefreshToken : {}", refreshToken);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken update(RefreshToken refreshToken) {
        log.debug("Request to update RefreshToken : {}", refreshToken);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> partialUpdate(RefreshToken refreshToken) {
        log.debug("Request to partially update RefreshToken : {}", refreshToken);

        return refreshTokenRepository
            .findById(refreshToken.getId())
            .map(existingRefreshToken -> {
                if (refreshToken.getToken() != null) {
                    existingRefreshToken.setToken(refreshToken.getToken());
                }
                if (refreshToken.getExpiryDate() != null) {
                    existingRefreshToken.setExpiryDate(refreshToken.getExpiryDate());
                }

                return existingRefreshToken;
            })
            .map(refreshTokenRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RefreshToken> findAll(Pageable pageable) {
        log.debug("Request to get all RefreshTokens");
        return refreshTokenRepository.findAll(pageable);
    }

    public Page<RefreshToken> findAllWithEagerRelationships(Pageable pageable) {
        return refreshTokenRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findOne(Long id) {
        log.debug("Request to get RefreshToken : {}", id);
        return refreshTokenRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RefreshToken : {}", id);
        refreshTokenRepository.deleteById(id);
    }
}
