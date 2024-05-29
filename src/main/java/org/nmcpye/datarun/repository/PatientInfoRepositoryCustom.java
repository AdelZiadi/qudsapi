package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.PatientInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the PatientInfo entity.
 */
@Repository
public interface PatientInfoRepositoryCustom extends PatientInfoRepository {
    default Optional<PatientInfo> findOneWithEagerRelationshipsByUser(Long id) {
        return this.findOneWithToOneRelationshipsByUser(id);
    }

    default List<PatientInfo> findAllWithEagerRelationshipsByUser() {
        return this.findAllWithToOneRelationshipsByUser();
    }

    default Page<PatientInfo> findAllWithEagerRelationshipsByUser(Pageable pageable) {
        return this.findAllWithToOneRelationshipsByUser(pageable);
    }

    @Query(
        value = "select patientInfo from PatientInfo patientInfo " +
            "left join patientInfo.location",
        countQuery = "select count(patientInfo) from PatientInfo patientInfo"
    )
    Page<PatientInfo> findAllByUser(Pageable pageable);

    @Query(
        value = "select patientInfo from PatientInfo patientInfo " +
            "left join fetch patientInfo.location",
        countQuery = "select count(patientInfo) from PatientInfo patientInfo"
    )
    Page<PatientInfo> findAllWithToOneRelationshipsByUser(Pageable pageable);

    @Query("select patientInfo from PatientInfo patientInfo " +
        "left join fetch patientInfo.location")
    List<PatientInfo> findAllWithToOneRelationshipsByUser();

    @Query("select patientInfo from PatientInfo patientInfo " +
        "left join fetch patientInfo.location where patientInfo.id =:id")
    Optional<PatientInfo> findOneWithToOneRelationshipsByUser(@Param("id") Long id);
}
