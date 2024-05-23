package org.nmcpye.datarun.service.impl;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.Campaign;
import org.nmcpye.datarun.repository.CampaignRepository;
import org.nmcpye.datarun.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.Campaign}.
 */
@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

    private final CampaignRepository campaignRepository;

    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign save(Campaign campaign) {
        log.debug("Request to save Campaign : {}", campaign);
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign update(Campaign campaign) {
        log.debug("Request to update Campaign : {}", campaign);
        return campaignRepository.save(campaign);
    }

    @Override
    public Optional<Campaign> partialUpdate(Campaign campaign) {
        log.debug("Request to partially update Campaign : {}", campaign);

        return campaignRepository
            .findById(campaign.getId())
            .map(existingCampaign -> {
                if (campaign.getCampaignCode() != null) {
                    existingCampaign.setCampaignCode(campaign.getCampaignCode());
                }
                if (campaign.getCampaignStartedDate() != null) {
                    existingCampaign.setCampaignStartedDate(campaign.getCampaignStartedDate());
                }
                if (campaign.getCampaignDays() != null) {
                    existingCampaign.setCampaignDays(campaign.getCampaignDays());
                }
                if (campaign.getCampaignYear() != null) {
                    existingCampaign.setCampaignYear(campaign.getCampaignYear());
                }
                if (campaign.getCampaignNote() != null) {
                    existingCampaign.setCampaignNote(campaign.getCampaignNote());
                }
                if (campaign.getEnabled() != null) {
                    existingCampaign.setEnabled(campaign.getEnabled());
                }

                return existingCampaign;
            })
            .map(campaignRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Campaign> findAll() {
        log.debug("Request to get all Campaigns");
        return campaignRepository.findAll();
    }

    public Page<Campaign> findAllWithEagerRelationships(Pageable pageable) {
        return campaignRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Campaign> findOne(Long id) {
        log.debug("Request to get Campaign : {}", id);
        return campaignRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Campaign : {}", id);
        campaignRepository.deleteById(id);
    }
}
