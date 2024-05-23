package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.VillageLocationTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class VillageLocationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VillageLocation.class);
        VillageLocation villageLocation1 = getVillageLocationSample1();
        VillageLocation villageLocation2 = new VillageLocation();
        assertThat(villageLocation1).isNotEqualTo(villageLocation2);

        villageLocation2.setId(villageLocation1.getId());
        assertThat(villageLocation1).isEqualTo(villageLocation2);

        villageLocation2 = getVillageLocationSample2();
        assertThat(villageLocation1).isNotEqualTo(villageLocation2);
    }
}
