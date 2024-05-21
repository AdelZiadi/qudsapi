package org.nmcpye.datarun.service.impl;

import java.util.Optional;
import org.nmcpye.datarun.domain.ItnsVillageHousesDetail;
import org.nmcpye.datarun.repository.ItnsVillageHousesDetailRepository;
import org.nmcpye.datarun.service.ItnsVillageHousesDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link org.nmcpye.datarun.domain.ItnsVillageHousesDetail}.
 */
@Service
@Transactional
public class ItnsVillageHousesDetailServiceImpl implements ItnsVillageHousesDetailService {

    private final Logger log = LoggerFactory.getLogger(ItnsVillageHousesDetailServiceImpl.class);

    private final ItnsVillageHousesDetailRepository itnsVillageHousesDetailRepository;

    public ItnsVillageHousesDetailServiceImpl(ItnsVillageHousesDetailRepository itnsVillageHousesDetailRepository) {
        this.itnsVillageHousesDetailRepository = itnsVillageHousesDetailRepository;
    }

    @Override
    public ItnsVillageHousesDetail save(ItnsVillageHousesDetail itnsVillageHousesDetail) {
        log.debug("Request to save ItnsVillageHousesDetail : {}", itnsVillageHousesDetail);
        return itnsVillageHousesDetailRepository.save(itnsVillageHousesDetail);
    }

    @Override
    public ItnsVillageHousesDetail update(ItnsVillageHousesDetail itnsVillageHousesDetail) {
        log.debug("Request to update ItnsVillageHousesDetail : {}", itnsVillageHousesDetail);
        return itnsVillageHousesDetailRepository.save(itnsVillageHousesDetail);
    }

    @Override
    public Optional<ItnsVillageHousesDetail> partialUpdate(ItnsVillageHousesDetail itnsVillageHousesDetail) {
        log.debug("Request to partially update ItnsVillageHousesDetail : {}", itnsVillageHousesDetail);

        return itnsVillageHousesDetailRepository
            .findById(itnsVillageHousesDetail.getId())
            .map(existingItnsVillageHousesDetail -> {
                if (itnsVillageHousesDetail.getSubmissionUuid() != null) {
                    existingItnsVillageHousesDetail.setSubmissionUuid(itnsVillageHousesDetail.getSubmissionUuid());
                }
                if (itnsVillageHousesDetail.getDeleted() != null) {
                    existingItnsVillageHousesDetail.setDeleted(itnsVillageHousesDetail.getDeleted());
                }
                if (itnsVillageHousesDetail.getHouseUuid() != null) {
                    existingItnsVillageHousesDetail.setHouseUuid(itnsVillageHousesDetail.getHouseUuid());
                }
                if (itnsVillageHousesDetail.getCouponid() != null) {
                    existingItnsVillageHousesDetail.setCouponid(itnsVillageHousesDetail.getCouponid());
                }
                if (itnsVillageHousesDetail.getHname() != null) {
                    existingItnsVillageHousesDetail.setHname(itnsVillageHousesDetail.getHname());
                }
                if (itnsVillageHousesDetail.getMale() != null) {
                    existingItnsVillageHousesDetail.setMale(itnsVillageHousesDetail.getMale());
                }
                if (itnsVillageHousesDetail.getFemale() != null) {
                    existingItnsVillageHousesDetail.setFemale(itnsVillageHousesDetail.getFemale());
                }
                if (itnsVillageHousesDetail.getPregnant() != null) {
                    existingItnsVillageHousesDetail.setPregnant(itnsVillageHousesDetail.getPregnant());
                }
                if (itnsVillageHousesDetail.getPopulation() != null) {
                    existingItnsVillageHousesDetail.setPopulation(itnsVillageHousesDetail.getPopulation());
                }
                if (itnsVillageHousesDetail.getMaleChild() != null) {
                    existingItnsVillageHousesDetail.setMaleChild(itnsVillageHousesDetail.getMaleChild());
                }
                if (itnsVillageHousesDetail.getFemaleChild() != null) {
                    existingItnsVillageHousesDetail.setFemaleChild(itnsVillageHousesDetail.getFemaleChild());
                }
                if (itnsVillageHousesDetail.getDisplaced() != null) {
                    existingItnsVillageHousesDetail.setDisplaced(itnsVillageHousesDetail.getDisplaced());
                }
                if (itnsVillageHousesDetail.getItns() != null) {
                    existingItnsVillageHousesDetail.setItns(itnsVillageHousesDetail.getItns());
                }
                if (itnsVillageHousesDetail.getComment() != null) {
                    existingItnsVillageHousesDetail.setComment(itnsVillageHousesDetail.getComment());
                }

                return existingItnsVillageHousesDetail;
            })
            .map(itnsVillageHousesDetailRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ItnsVillageHousesDetail> findAll(Pageable pageable) {
        log.debug("Request to get all ItnsVillageHousesDetails");
        return itnsVillageHousesDetailRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ItnsVillageHousesDetail> findOne(Long id) {
        log.debug("Request to get ItnsVillageHousesDetail : {}", id);
        return itnsVillageHousesDetailRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ItnsVillageHousesDetail : {}", id);
        itnsVillageHousesDetailRepository.deleteById(id);
    }
}
