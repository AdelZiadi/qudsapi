package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.CampaignTypeTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class CampaignTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignType.class);
        CampaignType campaignType1 = getCampaignTypeSample1();
        CampaignType campaignType2 = new CampaignType();
        assertThat(campaignType1).isNotEqualTo(campaignType2);

        campaignType2.setId(campaignType1.getId());
        assertThat(campaignType1).isEqualTo(campaignType2);

        campaignType2 = getCampaignTypeSample2();
        assertThat(campaignType1).isNotEqualTo(campaignType2);
    }
}
