package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.MVillagesLocations;
import org.nmcpye.datarun.repository.MVillagesLocationsRepository;
import org.nmcpye.datarun.service.MVillagesLocationsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.MVillagesLocations}.
 */
@Service
@Transactional
public class MVillagesLocationsServiceImpl implements MVillagesLocationsService {

    private final Logger log = LoggerFactory.getLogger(MVillagesLocationsServiceImpl.class);

    private final MVillagesLocationsRepository mVillagesLocationsRepository;

    public MVillagesLocationsServiceImpl(MVillagesLocationsRepository mVillagesLocationsRepository) {
        this.mVillagesLocationsRepository = mVillagesLocationsRepository;
    }

    @Override
    public MVillagesLocations save(MVillagesLocations mVillagesLocations) {
        log.debug("Request to save MVillagesLocations : {}", mVillagesLocations);
        return mVillagesLocationsRepository.save(mVillagesLocations);
    }

    @Override
    public MVillagesLocations update(MVillagesLocations mVillagesLocations) {
        log.debug("Request to update MVillagesLocations : {}", mVillagesLocations);
        return mVillagesLocationsRepository.save(mVillagesLocations);
    }

    @Override
    public Optional<MVillagesLocations> partialUpdate(MVillagesLocations mVillagesLocations) {
        log.debug("Request to partially update MVillagesLocations : {}", mVillagesLocations);

        return mVillagesLocationsRepository
            .findById(mVillagesLocations.getId())
            .map(existingMVillagesLocations -> {
                if (mVillagesLocations.getPpcCode() != null) {
                    existingMVillagesLocations.setPpcCode(mVillagesLocations.getPpcCode());
                }
                if (mVillagesLocations.getMappingStatus() != null) {
                    existingMVillagesLocations.setMappingStatus(mVillagesLocations.getMappingStatus());
                }
                if (mVillagesLocations.getDistrictCode() != null) {
                    existingMVillagesLocations.setDistrictCode(mVillagesLocations.getDistrictCode());
                }
                if (mVillagesLocations.getVillageUid() != null) {
                    existingMVillagesLocations.setVillageUid(mVillagesLocations.getVillageUid());
                }
                if (mVillagesLocations.getSubdistrictName() != null) {
                    existingMVillagesLocations.setSubdistrictName(mVillagesLocations.getSubdistrictName());
                }
                if (mVillagesLocations.getVillageName() != null) {
                    existingMVillagesLocations.setVillageName(mVillagesLocations.getVillageName());
                }
                if (mVillagesLocations.getSubvillageName() != null) {
                    existingMVillagesLocations.setSubvillageName(mVillagesLocations.getSubvillageName());
                }
                if (mVillagesLocations.getPpdName() != null) {
                    existingMVillagesLocations.setPpdName(mVillagesLocations.getPpdName());
                }
                if (mVillagesLocations.getUrbanRuralId() != null) {
                    existingMVillagesLocations.setUrbanRuralId(mVillagesLocations.getUrbanRuralId());
                }
                if (mVillagesLocations.getUrbanRural() != null) {
                    existingMVillagesLocations.setUrbanRural(mVillagesLocations.getUrbanRural());
                }
                if (mVillagesLocations.getSettlement() != null) {
                    existingMVillagesLocations.setSettlement(mVillagesLocations.getSettlement());
                }
                if (mVillagesLocations.getPop2004() != null) {
                    existingMVillagesLocations.setPop2004(mVillagesLocations.getPop2004());
                }
                if (mVillagesLocations.getPop2022() != null) {
                    existingMVillagesLocations.setPop2022(mVillagesLocations.getPop2022());
                }
                if (mVillagesLocations.getLongitude() != null) {
                    existingMVillagesLocations.setLongitude(mVillagesLocations.getLongitude());
                }
                if (mVillagesLocations.getLatitude() != null) {
                    existingMVillagesLocations.setLatitude(mVillagesLocations.getLatitude());
                }
                if (mVillagesLocations.getPpcCodeGis() != null) {
                    existingMVillagesLocations.setPpcCodeGis(mVillagesLocations.getPpcCodeGis());
                }
                if (mVillagesLocations.getLevel() != null) {
                    existingMVillagesLocations.setLevel(mVillagesLocations.getLevel());
                }

                return existingMVillagesLocations;
            })
            .map(mVillagesLocationsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MVillagesLocations> findAll(Pageable pageable) {
        log.debug("Request to get all MVillagesLocations");
        return mVillagesLocationsRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MVillagesLocations> findOne(Long id) {
        log.debug("Request to get MVillagesLocations : {}", id);
        return mVillagesLocationsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MVillagesLocations : {}", id);
        mVillagesLocationsRepository.deleteById(id);
    }
}
