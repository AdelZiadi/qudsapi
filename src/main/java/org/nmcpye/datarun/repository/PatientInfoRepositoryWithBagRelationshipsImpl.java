package org.nmcpye.datarun.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.nmcpye.datarun.domain.PatientInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class PatientInfoRepositoryWithBagRelationshipsImpl
    implements PatientInfoRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String ASSIGNMENTS_PARAMETER = "patientInfos";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<PatientInfo> fetchBagRelationships(Optional<PatientInfo> patientInfo) {
        return patientInfo.map(this::fetchChvRegisters);
    }

    @Override
    public Page<PatientInfo> fetchBagRelationships(Page<PatientInfo> patientInfos) {
        return new PageImpl<>(fetchBagRelationships(patientInfos.getContent()), patientInfos.getPageable(), patientInfos.getTotalElements());
    }

    @Override
    public List<PatientInfo> fetchBagRelationships(List<PatientInfo> patientInfos) {
        return Optional.of(patientInfos).map(this::fetchChvRegisters).orElse(Collections.emptyList());
    }

    PatientInfo fetchChvRegisters(PatientInfo result) {
        return entityManager
            .createQuery(
                "select patientInfo from PatientInfo patientInfo " +
                    "left join fetch patientInfo.chvRegisters " +
                    "where patientInfo.id = :id",
                PatientInfo.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<PatientInfo> fetchChvRegisters(List<PatientInfo> patientInfos) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, patientInfos.size()).forEach(index -> order.put(patientInfos.get(index).getId(), index));
        List<PatientInfo> result = entityManager
            .createQuery(
                "select patientInfo from PatientInfo patientInfo " +
                    "left join fetch patientInfo.chvRegisters " +
                    "where patientInfo in :patientInfos",
                PatientInfo.class
            )
            .setParameter(ASSIGNMENTS_PARAMETER, patientInfos)
            .getResultList();
        Collections.sort(result, Comparator.comparingInt(o -> order.get(o.getId())));
        return result;
    }
}
