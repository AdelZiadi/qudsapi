package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ActivityTestSamples.*;
import static org.nmcpye.datarun.domain.AssignmentTestSamples.*;
import static org.nmcpye.datarun.domain.ChvRegisterTestSamples.*;
import static org.nmcpye.datarun.domain.PatientInfoTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;

import java.util.HashSet;
import java.util.Set;
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
    void chvRegisterTest() throws Exception {
        PatientInfo patientInfo = getPatientInfoRandomSampleGenerator();
        ChvRegister chvRegisterBack = getChvRegisterRandomSampleGenerator();

        patientInfo.addChvRegister(chvRegisterBack);
        assertThat(patientInfo.getChvRegisters()).containsOnly(chvRegisterBack);
        assertThat(chvRegisterBack.getPatient()).isEqualTo(patientInfo);

        patientInfo.removeChvRegister(chvRegisterBack);
        assertThat(patientInfo.getChvRegisters()).doesNotContain(chvRegisterBack);
        assertThat(chvRegisterBack.getPatient()).isNull();

        patientInfo.chvRegisters(new HashSet<>(Set.of(chvRegisterBack)));
        assertThat(patientInfo.getChvRegisters()).containsOnly(chvRegisterBack);
        assertThat(chvRegisterBack.getPatient()).isEqualTo(patientInfo);

        patientInfo.setChvRegisters(new HashSet<>());
        assertThat(patientInfo.getChvRegisters()).doesNotContain(chvRegisterBack);
        assertThat(chvRegisterBack.getPatient()).isNull();
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

    @Test
    void activityTest() throws Exception {
        PatientInfo patientInfo = getPatientInfoRandomSampleGenerator();
        Activity activityBack = getActivityRandomSampleGenerator();

        patientInfo.setActivity(activityBack);
        assertThat(patientInfo.getActivity()).isEqualTo(activityBack);

        patientInfo.activity(null);
        assertThat(patientInfo.getActivity()).isNull();
    }

    @Test
    void teamTest() throws Exception {
        PatientInfo patientInfo = getPatientInfoRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        patientInfo.setTeam(teamBack);
        assertThat(patientInfo.getTeam()).isEqualTo(teamBack);

        patientInfo.team(null);
        assertThat(patientInfo.getTeam()).isNull();
    }
}
