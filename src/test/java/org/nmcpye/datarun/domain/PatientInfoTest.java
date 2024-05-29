package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.AssignmentTestSamples.*;
import static org.nmcpye.datarun.domain.PatientInfoTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class PatientInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientInfo.class);
        PatientInfo patientInfo1 = getPatientInfoSample1();
        PatientInfo patientInfo2 = new PatientInfo();
        assertThat(patientInfo1).isNotEqualTo(patientInfo2);

        patientInfo2.setId(patientInfo1.getId());
        assertThat(patientInfo1).isEqualTo(patientInfo2);

        patientInfo2 = getPatientInfoSample2();
        assertThat(patientInfo1).isNotEqualTo(patientInfo2);
    }

    @Test
    void locationTest() throws Exception {
        PatientInfo patientInfo = getPatientInfoRandomSampleGenerator();
        Assignment assignmentBack = getAssignmentRandomSampleGenerator();

        patientInfo.setLocation(assignmentBack);
        assertThat(patientInfo.getLocation()).isEqualTo(assignmentBack);

        patientInfo.location(null);
        assertThat(patientInfo.getLocation()).isNull();
    }
}
