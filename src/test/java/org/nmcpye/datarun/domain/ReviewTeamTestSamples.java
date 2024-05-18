package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReviewTeamTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReviewTeam getReviewTeamSample1() {
        return new ReviewTeam().id(1L).progressOrname("progressOrname1").progressOrUser("progressOrUser1");
    }

    public static ReviewTeam getReviewTeamSample2() {
        return new ReviewTeam().id(2L).progressOrname("progressOrname2").progressOrUser("progressOrUser2");
    }

    public static ReviewTeam getReviewTeamRandomSampleGenerator() {
        return new ReviewTeam()
            .id(longCount.incrementAndGet())
            .progressOrname(UUID.randomUUID().toString())
            .progressOrUser(UUID.randomUUID().toString());
    }
}
