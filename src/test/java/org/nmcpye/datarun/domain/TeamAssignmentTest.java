package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.CampaignTestSamples.*;
import static org.nmcpye.datarun.domain.MVillagesLocationsTestSamples.*;
import static org.nmcpye.datarun.domain.TeamAssignmentTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;
import static org.nmcpye.datarun.domain.WarehouseTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class TeamAssignmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeamAssignment.class);
        TeamAssignment teamAssignment1 = getTeamAssignmentSample1();
        TeamAssignment teamAssignment2 = new TeamAssignment();
        assertThat(teamAssignment1).isNotEqualTo(teamAssignment2);

        teamAssignment2.setId(teamAssignment1.getId());
        assertThat(teamAssignment1).isEqualTo(teamAssignment2);

        teamAssignment2 = getTeamAssignmentSample2();
        assertThat(teamAssignment1).isNotEqualTo(teamAssignment2);
    }

    @Test
    void campaignTest() throws Exception {
        TeamAssignment teamAssignment = getTeamAssignmentRandomSampleGenerator();
        Campaign campaignBack = getCampaignRandomSampleGenerator();

        teamAssignment.setCampaign(campaignBack);
        assertThat(teamAssignment.getCampaign()).isEqualTo(campaignBack);

        teamAssignment.campaign(null);
        assertThat(teamAssignment.getCampaign()).isNull();
    }

    @Test
    void locationTest() throws Exception {
        TeamAssignment teamAssignment = getTeamAssignmentRandomSampleGenerator();
        MVillagesLocations mVillagesLocationsBack = getMVillagesLocationsRandomSampleGenerator();

        teamAssignment.setLocation(mVillagesLocationsBack);
        assertThat(teamAssignment.getLocation()).isEqualTo(mVillagesLocationsBack);

        teamAssignment.location(null);
        assertThat(teamAssignment.getLocation()).isNull();
    }

    @Test
    void teamTest() throws Exception {
        TeamAssignment teamAssignment = getTeamAssignmentRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        teamAssignment.setTeam(teamBack);
        assertThat(teamAssignment.getTeam()).isEqualTo(teamBack);

        teamAssignment.team(null);
        assertThat(teamAssignment.getTeam()).isNull();
    }

    @Test
    void warehouseTest() throws Exception {
        TeamAssignment teamAssignment = getTeamAssignmentRandomSampleGenerator();
        Warehouse warehouseBack = getWarehouseRandomSampleGenerator();

        teamAssignment.setWarehouse(warehouseBack);
        assertThat(teamAssignment.getWarehouse()).isEqualTo(warehouseBack);

        teamAssignment.warehouse(null);
        assertThat(teamAssignment.getWarehouse()).isNull();
    }
}
