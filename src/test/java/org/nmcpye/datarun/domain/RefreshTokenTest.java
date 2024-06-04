package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.RefreshTokenTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class RefreshTokenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefreshToken.class);
        RefreshToken refreshToken1 = getRefreshTokenSample1();
        RefreshToken refreshToken2 = new RefreshToken();
        assertThat(refreshToken1).isNotEqualTo(refreshToken2);

        refreshToken2.setId(refreshToken1.getId());
        assertThat(refreshToken1).isEqualTo(refreshToken2);

        refreshToken2 = getRefreshTokenSample2();
        assertThat(refreshToken1).isNotEqualTo(refreshToken2);
    }
}
