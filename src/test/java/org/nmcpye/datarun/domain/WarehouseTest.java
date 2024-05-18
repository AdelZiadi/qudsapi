package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.CampaignTestSamples.*;
import static org.nmcpye.datarun.domain.WarehouseTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class WarehouseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Warehouse.class);
        Warehouse warehouse1 = getWarehouseSample1();
        Warehouse warehouse2 = new Warehouse();
        assertThat(warehouse1).isNotEqualTo(warehouse2);

        warehouse2.setId(warehouse1.getId());
        assertThat(warehouse1).isEqualTo(warehouse2);

        warehouse2 = getWarehouseSample2();
        assertThat(warehouse1).isNotEqualTo(warehouse2);
    }

    @Test
    void campaignTest() throws Exception {
        Warehouse warehouse = getWarehouseRandomSampleGenerator();
        Campaign campaignBack = getCampaignRandomSampleGenerator();

        warehouse.setCampaign(campaignBack);
        assertThat(warehouse.getCampaign()).isEqualTo(campaignBack);

        warehouse.campaign(null);
        assertThat(warehouse.getCampaign()).isNull();
    }
}
