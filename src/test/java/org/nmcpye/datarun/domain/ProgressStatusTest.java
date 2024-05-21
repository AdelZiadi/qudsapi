package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ProgressStatusTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class ProgressStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgressStatus.class);
        ProgressStatus progressStatus1 = getProgressStatusSample1();
        ProgressStatus progressStatus2 = new ProgressStatus();
        assertThat(progressStatus1).isNotEqualTo(progressStatus2);

        progressStatus2.setId(progressStatus1.getId());
        assertThat(progressStatus1).isEqualTo(progressStatus2);

        progressStatus2 = getProgressStatusSample2();
        assertThat(progressStatus1).isNotEqualTo(progressStatus2);
    }
}
