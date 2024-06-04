package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.RefreshToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.RefreshToken}.
 */
public interface RefreshTokenService {
    /**
     * Save a refreshToken.
     *
     * @param refreshToken the entity to save.
     * @return the persisted entity.
     */
    RefreshToken save(RefreshToken refreshToken);

    /**
     * Updates a refreshToken.
     *
     * @param refreshToken the entity to update.
     * @return the persisted entity.
     */
    RefreshToken update(RefreshToken refreshToken);

    /**
     * Partially updates a refreshToken.
     *
     * @param refreshToken the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RefreshToken> partialUpdate(RefreshToken refreshToken);

    /**
     * Get all the refreshTokens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefreshToken> findAll(Pageable pageable);

    /**
     * Get all the refreshTokens with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RefreshToken> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" refreshToken.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RefreshToken> findOne(Long id);

    /**
     * Delete the "id" refreshToken.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
