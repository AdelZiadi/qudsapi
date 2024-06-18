package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.TeamFormAccessTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class TeamFormAccessTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeamFormAccess.class);
        TeamFormAccess teamFormAccess1 = getTeamFormAccessSample1();
        TeamFormAccess teamFormAccess2 = new TeamFormAccess();
        assertThat(teamFormAccess1).isNotEqualTo(teamFormAccess2);

        teamFormAccess2.setId(teamFormAccess1.getId());
        assertThat(teamFormAccess1).isEqualTo(teamFormAccess2);

        teamFormAccess2 = getTeamFormAccessSample2();
        assertThat(teamFormAccess1).isNotEqualTo(teamFormAccess2);
    }
}
