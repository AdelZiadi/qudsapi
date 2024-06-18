package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TeamFormAccessTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TeamFormAccess getTeamFormAccessSample1() {
        return new TeamFormAccess().id(1L).uid("uid1").code("code1").name("name1").sessions(1).people(1).comment("comment1");
    }

    public static TeamFormAccess getTeamFormAccessSample2() {
        return new TeamFormAccess().id(2L).uid("uid2").code("code2").name("name2").sessions(2).people(2).comment("comment2");
    }

    public static TeamFormAccess getTeamFormAccessRandomSampleGenerator() {
        return new TeamFormAccess()
            .id(longCount.incrementAndGet())
            .uid(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .sessions(intCount.incrementAndGet())
            .people(intCount.incrementAndGet())
            .comment(UUID.randomUUID().toString());
    }
}
