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
public interface PatientInfoRepositoryCustom
    extends PatientInfoRepositoryWithBagRelationships, PatientInfoRepository {

    Optional<PatientInfo> findByUid(String uid);

    default Optional<PatientInfo> findOneWithEagerRelationshipsByUser(Long id) {
        return this.fetchBagRelationships(this.findOneWithToOneRelationshipsByUser(id));
    }

    default List<PatientInfo> findAllWithEagerRelationshipsByUser() {
        return this.fetchBagRelationships(this.findAllWithToOneRelationshipsByUser());
    }

    default Page<PatientInfo> findAllWithEagerRelationshipsByUser(Pageable pageable) {
        return this.fetchBagRelationships(this.findAllWithToOneRelationshipsByUser(pageable));
    }

    @Query(
        value = "select patientInfo from PatientInfo patientInfo " +
            "left join patientInfo.team " +
            "left join patientInfo.location " +
            "left join patientInfo.activity " +
            "WHERE patientInfo.team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(patientInfo) from PatientInfo patientInfo " +
            "WHERE patientInfo.team.userInfo.login = ?#{authentication.name}"
    )
    Page<PatientInfo> findAllByUser(Pageable pageable);

    @Query(
        value = "select patientInfo from PatientInfo patientInfo " +
            "left join fetch patientInfo.team " +
            "left join fetch patientInfo.location " +
            "left join fetch patientInfo.activity " +
            "WHERE patientInfo.team.userInfo.login = ?#{authentication.name}",
        countQuery = "select count(patientInfo) from PatientInfo patientInfo " +
            "WHERE patientInfo.team.userInfo.login = ?#{authentication.name}"
    )
    Page<PatientInfo> findAllWithToOneRelationshipsByUser(Pageable pageable);

    @Query(
        "select patientInfo from PatientInfo patientInfo " +
            "left join fetch patientInfo.team " +
            "left join fetch patientInfo.location " +
            "left join fetch patientInfo.activity " +
            "WHERE patientInfo.team.userInfo.login = ?#{authentication.name}"
    )
    List<PatientInfo> findAllWithToOneRelationshipsByUser();

    @Query(
        "select patientInfo from PatientInfo patientInfo " +
            "left join fetch patientInfo.team " +
            "left join fetch patientInfo.location " +
            "left join fetch patientInfo.activity " +
            "where patientInfo.id =:id and patientInfo.team.userInfo.login = ?#{authentication.name}"
    )
    Optional<PatientInfo> findOneWithToOneRelationshipsByUser(@Param("id") Long id);
}
