package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.MVillagesLocationsTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class MVillagesLocationsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MVillagesLocations.class);
        MVillagesLocations mVillagesLocations1 = getMVillagesLocationsSample1();
        MVillagesLocations mVillagesLocations2 = new MVillagesLocations();
        assertThat(mVillagesLocations1).isNotEqualTo(mVillagesLocations2);

        mVillagesLocations2.setId(mVillagesLocations1.getId());
        assertThat(mVillagesLocations1).isEqualTo(mVillagesLocations2);

        mVillagesLocations2 = getMVillagesLocationsSample2();
        assertThat(mVillagesLocations1).isNotEqualTo(mVillagesLocations2);
    }
}
