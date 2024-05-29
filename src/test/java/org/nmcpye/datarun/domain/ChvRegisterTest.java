package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ChvRegisterTestSamples.*;
import static org.nmcpye.datarun.domain.PatientInfoTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class ChvRegisterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChvRegister.class);
        ChvRegister chvRegister1 = getChvRegisterSample1();
        ChvRegister chvRegister2 = new ChvRegister();
        assertThat(chvRegister1).isNotEqualTo(chvRegister2);

        chvRegister2.setId(chvRegister1.getId());
        assertThat(chvRegister1).isEqualTo(chvRegister2);

        chvRegister2 = getChvRegisterSample2();
        assertThat(chvRegister1).isNotEqualTo(chvRegister2);
    }

    @Test
    void patientTest() throws Exception {
        ChvRegister chvRegister = getChvRegisterRandomSampleGenerator();
        PatientInfo patientInfoBack = getPatientInfoRandomSampleGenerator();

        chvRegister.setPatient(patientInfoBack);
        assertThat(chvRegister.getPatient()).isEqualTo(patientInfoBack);

        chvRegister.patient(null);
        assertThat(chvRegister.getPatient()).isNull();
    }

    @Test
    void teamTest() throws Exception {
        ChvRegister chvRegister = getChvRegisterRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        chvRegister.setTeam(teamBack);
        assertThat(chvRegister.getTeam()).isEqualTo(teamBack);

        chvRegister.team(null);
        assertThat(chvRegister.getTeam()).isNull();
    }
}
