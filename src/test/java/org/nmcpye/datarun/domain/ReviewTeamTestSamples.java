package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ReviewTeamTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ReviewTeam getReviewTeamSample1() {
        return new ReviewTeam()
            .id(1L)
            .uid("uid1")
            .code("code1")
            .name("name1")
            .user("user1")
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static ReviewTeam getReviewTeamSample2() {
        return new ReviewTeam()
            .id(2L)
            .uid("uid2")
            .code("code2")
            .name("name2")
            .user("user2")
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static ReviewTeam getReviewTeamRandomSampleGenerator() {
        return new ReviewTeam()
            .id(longCount.incrementAndGet())
            .uid(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .user(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
