package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TeamTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Team getTeamSample1() {
        return new Team()
            .id(1L)
            .teamLeader("teamLeader1")
            .teamNo(1L)
            .teamNote("teamNote1")
            .teamMobile("teamMobile1")
            .teamNoOfTeamWorkers(1)
            .mobility("mobility1")
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static Team getTeamSample2() {
        return new Team()
            .id(2L)
            .teamLeader("teamLeader2")
            .teamNo(2L)
            .teamNote("teamNote2")
            .teamMobile("teamMobile2")
            .teamNoOfTeamWorkers(2)
            .mobility("mobility2")
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static Team getTeamRandomSampleGenerator() {
        return new Team()
            .id(longCount.incrementAndGet())
            .teamLeader(UUID.randomUUID().toString())
            .teamNo(longCount.incrementAndGet())
            .teamNote(UUID.randomUUID().toString())
            .teamMobile(UUID.randomUUID().toString())
            .teamNoOfTeamWorkers(intCount.incrementAndGet())
            .mobility(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
