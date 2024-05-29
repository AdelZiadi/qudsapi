package org.nmcpye.datarun.service;

import java.util.Optional;
import org.nmcpye.datarun.domain.ChvRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.nmcpye.datarun.domain.ChvRegister}.
 */
public interface ChvRegisterService {
    /**
     * Save a chvRegister.
     *
     * @param chvRegister the entity to save.
     * @return the persisted entity.
     */
    ChvRegister save(ChvRegister chvRegister);

    /**
     * Updates a chvRegister.
     *
     * @param chvRegister the entity to update.
     * @return the persisted entity.
     */
    ChvRegister update(ChvRegister chvRegister);

    /**
     * Partially updates a chvRegister.
     *
     * @param chvRegister the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ChvRegister> partialUpdate(ChvRegister chvRegister);

    /**
     * Get all the chvRegisters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChvRegister> findAll(Pageable pageable);

    /**
     * Get all the chvRegisters with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ChvRegister> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" chvRegister.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ChvRegister> findOne(Long id);

    /**
     * Delete the "id" chvRegister.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
