package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ItnsVillageHousesDetailTestSamples.*;
import static org.nmcpye.datarun.domain.ItnsVillageTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class ItnsVillageHousesDetailTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ItnsVillageHousesDetail.class);
        ItnsVillageHousesDetail itnsVillageHousesDetail1 = getItnsVillageHousesDetailSample1();
        ItnsVillageHousesDetail itnsVillageHousesDetail2 = new ItnsVillageHousesDetail();
        assertThat(itnsVillageHousesDetail1).isNotEqualTo(itnsVillageHousesDetail2);

        itnsVillageHousesDetail2.setId(itnsVillageHousesDetail1.getId());
        assertThat(itnsVillageHousesDetail1).isEqualTo(itnsVillageHousesDetail2);

        itnsVillageHousesDetail2 = getItnsVillageHousesDetailSample2();
        assertThat(itnsVillageHousesDetail1).isNotEqualTo(itnsVillageHousesDetail2);
    }

    @Test
    void itnsVillageTest() throws Exception {
        ItnsVillageHousesDetail itnsVillageHousesDetail = getItnsVillageHousesDetailRandomSampleGenerator();
        ItnsVillage itnsVillageBack = getItnsVillageRandomSampleGenerator();

        itnsVillageHousesDetail.setItnsVillage(itnsVillageBack);
        assertThat(itnsVillageHousesDetail.getItnsVillage()).isEqualTo(itnsVillageBack);

        itnsVillageHousesDetail.itnsVillage(null);
        assertThat(itnsVillageHousesDetail.getItnsVillage()).isNull();
    }
}
