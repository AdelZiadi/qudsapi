package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ItnsVillageHousesDetailTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ItnsVillageHousesDetail getItnsVillageHousesDetailSample1() {
        return new ItnsVillageHousesDetail()
            .id(1L)
            .submissionUuid("submissionUuid1")
            .houseUuid("houseUuid1")
            .couponid(1L)
            .hname("hname1")
            .male(1)
            .female(1)
            .pregnant(1)
            .population(1)
            .maleChild(1)
            .femaleChild(1)
            .displaced(1)
            .itns(1)
            .comment("comment1");
    }

    public static ItnsVillageHousesDetail getItnsVillageHousesDetailSample2() {
        return new ItnsVillageHousesDetail()
            .id(2L)
            .submissionUuid("submissionUuid2")
            .houseUuid("houseUuid2")
            .couponid(2L)
            .hname("hname2")
            .male(2)
            .female(2)
            .pregnant(2)
            .population(2)
            .maleChild(2)
            .femaleChild(2)
            .displaced(2)
            .itns(2)
            .comment("comment2");
    }

    public static ItnsVillageHousesDetail getItnsVillageHousesDetailRandomSampleGenerator() {
        return new ItnsVillageHousesDetail()
            .id(longCount.incrementAndGet())
            .submissionUuid(UUID.randomUUID().toString())
            .houseUuid(UUID.randomUUID().toString())
            .couponid(longCount.incrementAndGet())
            .hname(UUID.randomUUID().toString())
            .male(intCount.incrementAndGet())
            .female(intCount.incrementAndGet())
            .pregnant(intCount.incrementAndGet())
            .population(intCount.incrementAndGet())
            .maleChild(intCount.incrementAndGet())
            .femaleChild(intCount.incrementAndGet())
            .displaced(intCount.incrementAndGet())
            .itns(intCount.incrementAndGet())
            .comment(UUID.randomUUID().toString());
    }
}
