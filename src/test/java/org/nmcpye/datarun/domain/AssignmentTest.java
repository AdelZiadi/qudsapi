package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ActivityTestSamples.*;
import static org.nmcpye.datarun.domain.AssignmentTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;
import static org.nmcpye.datarun.domain.VillageLocationTestSamples.*;
import static org.nmcpye.datarun.domain.WarehouseTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class AssignmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Assignment.class);
        Assignment assignment1 = getAssignmentSample1();
        Assignment assignment2 = new Assignment();
        assertThat(assignment1).isNotEqualTo(assignment2);

        assignment2.setId(assignment1.getId());
        assertThat(assignment1).isEqualTo(assignment2);

        assignment2 = getAssignmentSample2();
        assertThat(assignment1).isNotEqualTo(assignment2);
    }

    @Test
    void activityTest() throws Exception {
        Assignment assignment = getAssignmentRandomSampleGenerator();
        Activity activityBack = getActivityRandomSampleGenerator();

        assignment.setActivity(activityBack);
        assertThat(assignment.getActivity()).isEqualTo(activityBack);

        assignment.activity(null);
        assertThat(assignment.getActivity()).isNull();
    }

    @Test
    void organisationUnitTest() throws Exception {
        Assignment assignment = getAssignmentRandomSampleGenerator();
        VillageLocation villageLocationBack = getVillageLocationRandomSampleGenerator();

        assignment.setOrganisationUnit(villageLocationBack);
        assertThat(assignment.getOrganisationUnit()).isEqualTo(villageLocationBack);

        assignment.organisationUnit(null);
        assertThat(assignment.getOrganisationUnit()).isNull();
    }

    @Test
    void teamTest() throws Exception {
        Assignment assignment = getAssignmentRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        assignment.setTeam(teamBack);
        assertThat(assignment.getTeam()).isEqualTo(teamBack);

        assignment.team(null);
        assertThat(assignment.getTeam()).isNull();
    }

    @Test
    void warehouseTest() throws Exception {
        Assignment assignment = getAssignmentRandomSampleGenerator();
        Warehouse warehouseBack = getWarehouseRandomSampleGenerator();

        assignment.setWarehouse(warehouseBack);
        assertThat(assignment.getWarehouse()).isEqualTo(warehouseBack);

        assignment.warehouse(null);
        assertThat(assignment.getWarehouse()).isNull();
    }
}
