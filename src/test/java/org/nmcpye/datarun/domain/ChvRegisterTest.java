package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ActivityTestSamples.*;
import static org.nmcpye.datarun.domain.AssignmentTestSamples.*;
import static org.nmcpye.datarun.domain.ChvRegisterTestSamples.*;
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
    void locationTest() throws Exception {
        ChvRegister chvRegister = getChvRegisterRandomSampleGenerator();
        Assignment assignmentBack = getAssignmentRandomSampleGenerator();

        chvRegister.setLocation(assignmentBack);
        assertThat(chvRegister.getLocation()).isEqualTo(assignmentBack);

        chvRegister.location(null);
        assertThat(chvRegister.getLocation()).isNull();
    }

    @Test
    void activityTest() throws Exception {
        ChvRegister chvRegister = getChvRegisterRandomSampleGenerator();
        Activity activityBack = getActivityRandomSampleGenerator();

        chvRegister.setActivity(activityBack);
        assertThat(chvRegister.getActivity()).isEqualTo(activityBack);

        chvRegister.activity(null);
        assertThat(chvRegister.getActivity()).isNull();
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
