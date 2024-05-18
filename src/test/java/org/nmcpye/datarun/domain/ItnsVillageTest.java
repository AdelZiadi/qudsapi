package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.CampaignTestSamples.*;
import static org.nmcpye.datarun.domain.ItnsVillageHousesDetailTestSamples.*;
import static org.nmcpye.datarun.domain.ItnsVillageTestSamples.*;
import static org.nmcpye.datarun.domain.ProgressStatusTestSamples.*;
import static org.nmcpye.datarun.domain.TeamAssignmentTestSamples.*;
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
        TeamAssignment teamAssignmentBack = getTeamAssignmentRandomSampleGenerator();

        itnsVillage.setAssignment(teamAssignmentBack);
        assertThat(itnsVillage.getAssignment()).isEqualTo(teamAssignmentBack);

        itnsVillage.assignment(null);
        assertThat(itnsVillage.getAssignment()).isNull();
    }

    @Test
    void campaignTest() throws Exception {
        ItnsVillage itnsVillage = getItnsVillageRandomSampleGenerator();
        Campaign campaignBack = getCampaignRandomSampleGenerator();

        itnsVillage.setCampaign(campaignBack);
        assertThat(itnsVillage.getCampaign()).isEqualTo(campaignBack);

        itnsVillage.campaign(null);
        assertThat(itnsVillage.getCampaign()).isNull();
    }

    @Test
    void houseDetailTest() throws Exception {
        ItnsVillage itnsVillage = getItnsVillageRandomSampleGenerator();
        ItnsVillageHousesDetail itnsVillageHousesDetailBack = getItnsVillageHousesDetailRandomSampleGenerator();

        itnsVillage.addHouseDetail(itnsVillageHousesDetailBack);
        assertThat(itnsVillage.getHouseDetails()).containsOnly(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getVillageData()).isEqualTo(itnsVillage);

        itnsVillage.removeHouseDetail(itnsVillageHousesDetailBack);
        assertThat(itnsVillage.getHouseDetails()).doesNotContain(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getVillageData()).isNull();

        itnsVillage.houseDetails(new HashSet<>(Set.of(itnsVillageHousesDetailBack)));
        assertThat(itnsVillage.getHouseDetails()).containsOnly(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getVillageData()).isEqualTo(itnsVillage);

        itnsVillage.setHouseDetails(new HashSet<>());
        assertThat(itnsVillage.getHouseDetails()).doesNotContain(itnsVillageHousesDetailBack);
        assertThat(itnsVillageHousesDetailBack.getVillageData()).isNull();
    }
}
