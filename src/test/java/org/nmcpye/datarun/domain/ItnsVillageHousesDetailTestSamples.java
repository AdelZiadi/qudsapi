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
            .uid("uid1")
            .code("code1")
            .couponId(1L)
            .name("name1")
            .male(1)
            .female(1)
            .pregnant(1)
            .population(1)
            .maleChild(1)
            .femaleChild(1)
            .displaced(1)
            .itns(1)
            .comment("comment1")
            .submissionUuid("submissionUuid1")
            .houseUuid("houseUuid1")
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static ItnsVillageHousesDetail getItnsVillageHousesDetailSample2() {
        return new ItnsVillageHousesDetail()
            .id(2L)
            .uid("uid2")
            .code("code2")
            .couponId(2L)
            .name("name2")
            .male(2)
            .female(2)
            .pregnant(2)
            .population(2)
            .maleChild(2)
            .femaleChild(2)
            .displaced(2)
            .itns(2)
            .comment("comment2")
            .submissionUuid("submissionUuid2")
            .houseUuid("houseUuid2")
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static ItnsVillageHousesDetail getItnsVillageHousesDetailRandomSampleGenerator() {
        return new ItnsVillageHousesDetail()
            .id(longCount.incrementAndGet())
            .uid(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .couponId(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .male(intCount.incrementAndGet())
            .female(intCount.incrementAndGet())
            .pregnant(intCount.incrementAndGet())
            .population(intCount.incrementAndGet())
            .maleChild(intCount.incrementAndGet())
            .femaleChild(intCount.incrementAndGet())
            .displaced(intCount.incrementAndGet())
            .itns(intCount.incrementAndGet())
            .comment(UUID.randomUUID().toString())
            .submissionUuid(UUID.randomUUID().toString())
            .houseUuid(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
