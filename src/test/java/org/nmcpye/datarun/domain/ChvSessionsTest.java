package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ChvSessionsTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class ChvSessionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChvSessions.class);
        ChvSessions chvSessions1 = getChvSessionsSample1();
        ChvSessions chvSessions2 = new ChvSessions();
        assertThat(chvSessions1).isNotEqualTo(chvSessions2);

        chvSessions2.setId(chvSessions1.getId());
        assertThat(chvSessions1).isEqualTo(chvSessions2);

        chvSessions2 = getChvSessionsSample2();
        assertThat(chvSessions1).isNotEqualTo(chvSessions2);
    }

    @Test
    void teamTest() throws Exception {
        ChvSessions chvSessions = getChvSessionsRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        chvSessions.setTeam(teamBack);
        assertThat(chvSessions.getTeam()).isEqualTo(teamBack);

        chvSessions.team(null);
        assertThat(chvSessions.getTeam()).isNull();
    }
}
