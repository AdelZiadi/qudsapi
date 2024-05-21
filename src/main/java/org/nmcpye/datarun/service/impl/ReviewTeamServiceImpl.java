package org.nmcpye.datarun.service.impl;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.ReviewTeam;
import org.nmcpye.datarun.repository.ReviewTeamRepository;
import org.nmcpye.datarun.service.ReviewTeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.ReviewTeam}.
 */
@Service
@Transactional
public class ReviewTeamServiceImpl implements ReviewTeamService {

    private final Logger log = LoggerFactory.getLogger(ReviewTeamServiceImpl.class);

    private final ReviewTeamRepository reviewTeamRepository;

    public ReviewTeamServiceImpl(ReviewTeamRepository reviewTeamRepository) {
        this.reviewTeamRepository = reviewTeamRepository;
    }

    @Override
    public ReviewTeam save(ReviewTeam reviewTeam) {
        log.debug("Request to save ReviewTeam : {}", reviewTeam);
        return reviewTeamRepository.save(reviewTeam);
    }

    @Override
    public ReviewTeam update(ReviewTeam reviewTeam) {
        log.debug("Request to update ReviewTeam : {}", reviewTeam);
        return reviewTeamRepository.save(reviewTeam);
    }

    @Override
    public Optional<ReviewTeam> partialUpdate(ReviewTeam reviewTeam) {
        log.debug("Request to partially update ReviewTeam : {}", reviewTeam);

        return reviewTeamRepository
            .findById(reviewTeam.getId())
            .map(existingReviewTeam -> {
                if (reviewTeam.getUid() != null) {
                    existingReviewTeam.setUid(reviewTeam.getUid());
                }
                if (reviewTeam.getName() != null) {
                    existingReviewTeam.setName(reviewTeam.getName());
                }
                if (reviewTeam.getUser() != null) {
                    existingReviewTeam.setUser(reviewTeam.getUser());
                }

                return existingReviewTeam;
            })
            .map(reviewTeamRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewTeam> findAll() {
        log.debug("Request to get all ReviewTeams");
        return reviewTeamRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReviewTeam> findOne(Long id) {
        log.debug("Request to get ReviewTeam : {}", id);
        return reviewTeamRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReviewTeam : {}", id);
        reviewTeamRepository.deleteById(id);
    }
}
