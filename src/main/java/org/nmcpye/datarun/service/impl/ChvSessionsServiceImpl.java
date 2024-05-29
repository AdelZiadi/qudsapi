package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.ChvSessions;
import org.nmcpye.datarun.repository.ChvSessionsRepository;
import org.nmcpye.datarun.service.ChvSessionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.ChvSessions}.
 */
@Service
@Transactional
public class ChvSessionsServiceImpl implements ChvSessionsService {

    private final Logger log = LoggerFactory.getLogger(ChvSessionsServiceImpl.class);

    private final ChvSessionsRepository chvSessionsRepository;

    public ChvSessionsServiceImpl(ChvSessionsRepository chvSessionsRepository) {
        this.chvSessionsRepository = chvSessionsRepository;
    }

    @Override
    public ChvSessions save(ChvSessions chvSessions) {
        log.debug("Request to save ChvSessions : {}", chvSessions);
        return chvSessionsRepository.save(chvSessions);
    }

    @Override
    public ChvSessions update(ChvSessions chvSessions) {
        log.debug("Request to update ChvSessions : {}", chvSessions);
        chvSessions.setIsPersisted();
        return chvSessionsRepository.save(chvSessions);
    }

    @Override
    public Optional<ChvSessions> partialUpdate(ChvSessions chvSessions) {
        log.debug("Request to partially update ChvSessions : {}", chvSessions);

        return chvSessionsRepository
            .findById(chvSessions.getId())
            .map(existingChvSessions -> {
                if (chvSessions.getUid() != null) {
                    existingChvSessions.setUid(chvSessions.getUid());
                }
                if (chvSessions.getCode() != null) {
                    existingChvSessions.setCode(chvSessions.getCode());
                }
                if (chvSessions.getName() != null) {
                    existingChvSessions.setName(chvSessions.getName());
                }
                if (chvSessions.getSessionDate() != null) {
                    existingChvSessions.setSessionDate(chvSessions.getSessionDate());
                }
                if (chvSessions.getSubject() != null) {
                    existingChvSessions.setSubject(chvSessions.getSubject());
                }
                if (chvSessions.getSessions() != null) {
                    existingChvSessions.setSessions(chvSessions.getSessions());
                }
                if (chvSessions.getPeople() != null) {
                    existingChvSessions.setPeople(chvSessions.getPeople());
                }
                if (chvSessions.getComment() != null) {
                    existingChvSessions.setComment(chvSessions.getComment());
                }
                if (chvSessions.getStartEntryTime() != null) {
                    existingChvSessions.setStartEntryTime(chvSessions.getStartEntryTime());
                }
                if (chvSessions.getDeleted() != null) {
                    existingChvSessions.setDeleted(chvSessions.getDeleted());
                }
                if (chvSessions.getCreatedBy() != null) {
                    existingChvSessions.setCreatedBy(chvSessions.getCreatedBy());
                }
                if (chvSessions.getCreatedDate() != null) {
                    existingChvSessions.setCreatedDate(chvSessions.getCreatedDate());
                }
                if (chvSessions.getLastModifiedBy() != null) {
                    existingChvSessions.setLastModifiedBy(chvSessions.getLastModifiedBy());
                }
                if (chvSessions.getLastModifiedDate() != null) {
                    existingChvSessions.setLastModifiedDate(chvSessions.getLastModifiedDate());
                }

                return existingChvSessions;
            })
            .map(chvSessionsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChvSessions> findAll(Pageable pageable) {
        log.debug("Request to get all ChvSessions");
        return chvSessionsRepository.findAll(pageable);
    }

    public Page<ChvSessions> findAllWithEagerRelationships(Pageable pageable) {
        return chvSessionsRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChvSessions> findOne(Long id) {
        log.debug("Request to get ChvSessions : {}", id);
        return chvSessionsRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChvSessions : {}", id);
        chvSessionsRepository.deleteById(id);
    }
}
