package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ActivityTestSamples.*;
import static org.nmcpye.datarun.domain.ReviewTeamTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;
import static org.nmcpye.datarun.domain.WarehouseTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class TeamTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Team.class);
        Team team1 = getTeamSample1();
        Team team2 = new Team();
        assertThat(team1).isNotEqualTo(team2);

        team2.setId(team1.getId());
        assertThat(team1).isEqualTo(team2);

        team2 = getTeamSample2();
        assertThat(team1).isNotEqualTo(team2);
    }

    @Test
    void activityTest() throws Exception {
        Team team = getTeamRandomSampleGenerator();
        Activity activityBack = getActivityRandomSampleGenerator();

        team.setActivity(activityBack);
        assertThat(team.getActivity()).isEqualTo(activityBack);

        team.activity(null);
        assertThat(team.getActivity()).isNull();
    }

    @Test
    void operationRoomTest() throws Exception {
        Team team = getTeamRandomSampleGenerator();
        ReviewTeam reviewTeamBack = getReviewTeamRandomSampleGenerator();

        team.setOperationRoom(reviewTeamBack);
        assertThat(team.getOperationRoom()).isEqualTo(reviewTeamBack);

        team.operationRoom(null);
        assertThat(team.getOperationRoom()).isNull();
    }

    @Test
    void warehouseTest() throws Exception {
        Team team = getTeamRandomSampleGenerator();
        Warehouse warehouseBack = getWarehouseRandomSampleGenerator();

        team.setWarehouse(warehouseBack);
        assertThat(team.getWarehouse()).isEqualTo(warehouseBack);

        team.warehouse(null);
        assertThat(team.getWarehouse()).isNull();
    }
}
