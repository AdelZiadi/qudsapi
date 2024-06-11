package org.nmcpye.datarun.repository;

import org.nmcpye.datarun.domain.PatientInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PatientInfoRepositoryWithBagRelationships {
    Optional<PatientInfo> fetchBagRelationships(Optional<PatientInfo> patientInfos);

    List<PatientInfo> fetchBagRelationships(List<PatientInfo> patientInfos);

    Page<PatientInfo> fetchBagRelationships(Page<PatientInfo> patientInfos);
}
