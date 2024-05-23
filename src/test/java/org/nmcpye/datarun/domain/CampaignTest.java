package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.CampaignTestSamples.*;
import static org.nmcpye.datarun.domain.CampaignTypeTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class CampaignTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Campaign.class);
        Campaign campaign1 = getCampaignSample1();
        Campaign campaign2 = new Campaign();
        assertThat(campaign1).isNotEqualTo(campaign2);

        campaign2.setId(campaign1.getId());
        assertThat(campaign1).isEqualTo(campaign2);

        campaign2 = getCampaignSample2();
        assertThat(campaign1).isNotEqualTo(campaign2);
    }

    @Test
    void campaignTypeTest() throws Exception {
        Campaign campaign = getCampaignRandomSampleGenerator();
        CampaignType campaignTypeBack = getCampaignTypeRandomSampleGenerator();

        campaign.setCampaignType(campaignTypeBack);
        assertThat(campaign.getCampaignType()).isEqualTo(campaignTypeBack);

        campaign.campaignType(null);
        assertThat(campaign.getCampaignType()).isNull();
    }
}
