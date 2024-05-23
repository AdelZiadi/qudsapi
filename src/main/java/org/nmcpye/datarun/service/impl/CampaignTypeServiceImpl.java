package org.nmcpye.datarun.service.impl;

import java.util.List;
import java.util.Optional;
import org.nmcpye.datarun.domain.CampaignType;
import org.nmcpye.datarun.repository.CampaignTypeRepository;
import org.nmcpye.datarun.service.CampaignTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.CampaignType}.
 */
@Service
@Transactional
public class CampaignTypeServiceImpl implements CampaignTypeService {

    private final Logger log = LoggerFactory.getLogger(CampaignTypeServiceImpl.class);

    private final CampaignTypeRepository campaignTypeRepository;

    public CampaignTypeServiceImpl(CampaignTypeRepository campaignTypeRepository) {
        this.campaignTypeRepository = campaignTypeRepository;
    }

    @Override
    public CampaignType save(CampaignType campaignType) {
        log.debug("Request to save CampaignType : {}", campaignType);
        return campaignTypeRepository.save(campaignType);
    }

    @Override
    public CampaignType update(CampaignType campaignType) {
        log.debug("Request to update CampaignType : {}", campaignType);
        return campaignTypeRepository.save(campaignType);
    }

    @Override
    public Optional<CampaignType> partialUpdate(CampaignType campaignType) {
        log.debug("Request to partially update CampaignType : {}", campaignType);

        return campaignTypeRepository
            .findById(campaignType.getId())
            .map(existingCampaignType -> {
                if (campaignType.getCampaignType() != null) {
                    existingCampaignType.setCampaignType(campaignType.getCampaignType());
                }
                if (campaignType.getDescription() != null) {
                    existingCampaignType.setDescription(campaignType.getDescription());
                }

                return existingCampaignType;
            })
            .map(campaignTypeRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CampaignType> findAll() {
        log.debug("Request to get all CampaignTypes");
        return campaignTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CampaignType> findOne(Long id) {
        log.debug("Request to get CampaignType : {}", id);
        return campaignTypeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampaignType : {}", id);
        campaignTypeRepository.deleteById(id);
    }
}
