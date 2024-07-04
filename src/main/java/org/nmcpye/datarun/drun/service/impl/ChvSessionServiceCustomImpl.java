package org.nmcpye.datarun.drun.service.impl;

import org.nmcpye.datarun.domain.ChvSession;
import org.nmcpye.datarun.drun.repository.ChvSessionRepositoryCustom;
import org.nmcpye.datarun.drun.service.IdentifiableServiceImpl;
import org.nmcpye.datarun.drun.service.ChvSessionServiceCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Primary
@Transactional
public class ChvSessionServiceCustomImpl
    extends IdentifiableServiceImpl<ChvSession>
    implements ChvSessionServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ChvSessionServiceCustomImpl.class);

    private final ChvSessionRepositoryCustom chvSessionRepository;


    public ChvSessionServiceCustomImpl(ChvSessionRepositoryCustom chvSessionRepository) {
        super(chvSessionRepository);
        this.chvSessionRepository = chvSessionRepository;
    }

    @Override
    public Optional<ChvSession> partialUpdate(ChvSession chvSession) {
        log.debug("Request to partially update ChvSession : {}", chvSession);

        return chvSessionRepository
            .findById(chvSession.getId())
            .map(existingChvSession -> {
                if (chvSession.getUid() != null) {
                    existingChvSession.setUid(chvSession.getUid());
                }
                if (chvSession.getCode() != null) {
                    existingChvSession.setCode(chvSession.getCode());
                }
                if (chvSession.getName() != null) {
                    existingChvSession.setName(chvSession.getName());
                }
                if (chvSession.getSessionDate() != null) {
                    existingChvSession.setSessionDate(chvSession.getSessionDate());
                }
                if (chvSession.getSubject() != null) {
                    existingChvSession.setSubject(chvSession.getSubject());
                }
                if (chvSession.getSessions() != null) {
                    existingChvSession.setSessions(chvSession.getSessions());
                }
                if (chvSession.getPeople() != null) {
                    existingChvSession.setPeople(chvSession.getPeople());
                }
                if (chvSession.getComment() != null) {
                    existingChvSession.setComment(chvSession.getComment());
                }
                if (chvSession.getDeleted() != null) {
                    existingChvSession.setDeleted(chvSession.getDeleted());
                }
                if (chvSession.getStartEntryTime() != null) {
                    existingChvSession.setStartEntryTime(chvSession.getStartEntryTime());
                }
                if (chvSession.getFinishedEntryTime() != null) {
                    existingChvSession.setFinishedEntryTime(chvSession.getFinishedEntryTime());
                }
                if (chvSession.getStatus() != null) {
                    existingChvSession.setStatus(chvSession.getStatus());
                }
                if (chvSession.getCreatedBy() != null) {
                    existingChvSession.setCreatedBy(chvSession.getCreatedBy());
                }
                if (chvSession.getCreatedDate() != null) {
                    existingChvSession.setCreatedDate(chvSession.getCreatedDate());
                }
                if (chvSession.getLastModifiedBy() != null) {
                    existingChvSession.setLastModifiedBy(chvSession.getLastModifiedBy());
                }
                if (chvSession.getLastModifiedDate() != null) {
                    existingChvSession.setLastModifiedDate(chvSession.getLastModifiedDate());
                }

                return existingChvSession;
            })
            .map(chvSessionRepository::save);
    }
}
