package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ActivityTestSamples.*;
import static org.nmcpye.datarun.domain.AssignmentTestSamples.*;
import static org.nmcpye.datarun.domain.ItnsVillageHousesDetailTestSamples.*;
import static org.nmcpye.datarun.domain.ItnsVillageTestSamples.*;
import static org.nmcpye.datarun.domain.ProgressStatusTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class ItnsVillageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItnsVillage.class);
        ItnsVillage itnsVillage1 = getItnsVillageSample1();
        ItnsVillage itnsVillage2 = new ItnsVillage();
        assertThat(itnsVillage1).isNotEqualTo(itnsVillage2);

        itnsVillage2.setId(itnsVillage1.getId());
        assertThat(itnsVillage1).isEqualTo(itnsVillage2);

        itnsVillage2 = getItnsVillageSample2();
        assertThat(itnsVillage1).isNotEqualTo(itnsVillage2);
    }

    @Test
    void houseDetailTest() throws Exception {
        ItnsVillage itnsVillage = getItnsVillageRandomSampleGenerator();
        ItnsVillageHousesDetail itnsVillageHousesDetailBack = getItnsVillageHousesDetailRandomSampleGenerator();

        itnsVillage.addHouseDetail(itnsVillageHousesDetailBack);
        assertThat(itnsVillage.getHouseDetails()).containsOnly(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getItnsVillage()).isEqualTo(itnsVillage);

        itnsVillage.removeHouseDetail(itnsVillageHousesDetailBack);
        assertThat(itnsVillage.getHouseDetails()).doesNotContain(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getItnsVillage()).isNull();

        itnsVillage.houseDetails(new HashSet<>(Set.of(itnsVillageHousesDetailBack)));
        assertThat(itnsVillage.getHouseDetails()).containsOnly(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getItnsVillage()).isEqualTo(itnsVillage);

        itnsVillage.setHouseDetails(new HashSet<>());
        assertThat(itnsVillage.getHouseDetails()).doesNotContain(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getItnsVillage()).isNull();
    }

    @Test
    void progressStatusTest() throws Exception {
        ItnsVillage itnsVillage = getItnsVillageRandomSampleGenerator();
        ProgressStatus progressStatusBack = getProgressStatusRandomSampleGenerator();

        itnsVillage.setProgressStatus(progressStatusBack);
        assertThat(itnsVillage.getProgressStatus()).isEqualTo(progressStatusBack);

        itnsVillage.progressStatus(null);
        assertThat(itnsVillage.getProgressStatus()).isNull();
    }

    @Test
    void teamTest() throws Exception {
        ItnsVillage itnsVillage = getItnsVillageRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        itnsVillage.setTeam(teamBack);
        assertThat(itnsVillage.getTeam()).isEqualTo(teamBack);

        itnsVillage.team(null);
        assertThat(itnsVillage.getTeam()).isNull();
    }

    @Test
    void assignmentTest() throws Exception {
        ItnsVillage itnsVillage = getItnsVillageRandomSampleGenerator();
        Assignment assignmentBack = getAssignmentRandomSampleGenerator();

        itnsVillage.setAssignment(assignmentBack);
        assertThat(itnsVillage.getAssignment()).isEqualTo(assignmentBack);

        itnsVillage.assignment(null);
        assertThat(itnsVillage.getAssignment()).isNull();
    }

    @Test
    void activityTest() throws Exception {
        ItnsVillage itnsVillage = getItnsVillageRandomSampleGenerator();
        Activity activityBack = getActivityRandomSampleGenerator();

        itnsVillage.setActivity(activityBack);
        assertThat(itnsVillage.getActivity()).isEqualTo(activityBack);

        itnsVillage.activity(null);
        assertThat(itnsVillage.getActivity()).isNull();
    }
}
