package org.nmcpye.datarun.drun.service;

import org.nmcpye.datarun.domain.common.IdentifiableObject;
import org.nmcpye.datarun.drun.repository.IdentifiableRepository;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public abstract class IdentifiableServiceImpl<T extends IdentifiableObject>
    implements IdentifiableService<T> {
    private final Logger log = LoggerFactory.getLogger(IdentifiableServiceImpl.class);

    final protected IdentifiableRepository<T, Long> repository;

    public IdentifiableServiceImpl(IdentifiableRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findByUid(String uid) {
        return repository.findByUid(uid);
    }


    @Override
    public void deleteByUid(String uid) {
        repository.findByUid(uid).ifPresent(repository::delete);
    }

    @Override
    public T save(T object) {
        log.debug("Request to save T : {}", object);
        if (object.getUid() == null || object.getUid().isEmpty()) {
            object.setUid(CodeGenerator.generateUid());
        }
        return repository.save(object);
    }

    @Override
    public T update(T object) {
        log.debug("Request to update T : {}", object);
        object.setIsPersisted();
        return repository.save(object);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        log.debug("Request to get all ChvRegisters");
        return repository.findAllByUser(pageable);
    }

    public Page<T> findAllWithEagerRelationships(Pageable pageable) {
        return repository.findAllWithEagerRelationshipsByUser(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findOne(Long id) {
        log.debug("Request to get T : {}", id);
        return repository.findOneWithEagerRelationshipsByUser(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete T : {}", id);
        repository.deleteById(id);
    }
}

