package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ReviewTeamTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class ReviewTeamTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReviewTeam.class);
        ReviewTeam reviewTeam1 = getReviewTeamSample1();
        ReviewTeam reviewTeam2 = new ReviewTeam();
        assertThat(reviewTeam1).isNotEqualTo(reviewTeam2);

        reviewTeam2.setId(reviewTeam1.getId());
        assertThat(reviewTeam1).isEqualTo(reviewTeam2);

        reviewTeam2 = getReviewTeamSample2();
        assertThat(reviewTeam1).isNotEqualTo(reviewTeam2);
    }
}
