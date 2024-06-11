package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ActivityTestSamples.*;
import static org.nmcpye.datarun.domain.ChvSessionTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class ChvSessionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChvSession.class);
        ChvSession chvSession1 = getChvSessionSample1();
        ChvSession chvSession2 = new ChvSession();
        assertThat(chvSession1).isNotEqualTo(chvSession2);

        chvSession2.setId(chvSession1.getId());
        assertThat(chvSession1).isEqualTo(chvSession2);

        chvSession2 = getChvSessionSample2();
        assertThat(chvSession1).isNotEqualTo(chvSession2);
    }

    @Test
    void teamTest() throws Exception {
        ChvSession chvSession = getChvSessionRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        chvSession.setTeam(teamBack);
        assertThat(chvSession.getTeam()).isEqualTo(teamBack);

        chvSession.team(null);
        assertThat(chvSession.getTeam()).isNull();
    }

    @Test
    void activityTest() throws Exception {
        ChvSession chvSession = getChvSessionRandomSampleGenerator();
        Activity activityBack = getActivityRandomSampleGenerator();

        chvSession.setActivity(activityBack);
        assertThat(chvSession.getActivity()).isEqualTo(activityBack);

        chvSession.activity(null);
        assertThat(chvSession.getActivity()).isNull();
    }
}
