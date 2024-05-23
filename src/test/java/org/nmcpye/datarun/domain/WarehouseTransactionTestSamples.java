package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class WarehouseTransactionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static WarehouseTransaction getWarehouseTransactionSample1() {
        return new WarehouseTransaction()
            .id(1L)
            .imovUid("imovUid1")
            .phaseNo(1)
            .entryType("entryType1")
            .quantity(1)
            .notes("notes1")
            .personName("personName1")
            .workDayId(1)
            .submissionId(1L)
            .submissionUuid("submissionUuid1")
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static WarehouseTransaction getWarehouseTransactionSample2() {
        return new WarehouseTransaction()
            .id(2L)
            .imovUid("imovUid2")
            .phaseNo(2)
            .entryType("entryType2")
            .quantity(2)
            .notes("notes2")
            .personName("personName2")
            .workDayId(2)
            .submissionId(2L)
            .submissionUuid("submissionUuid2")
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static WarehouseTransaction getWarehouseTransactionRandomSampleGenerator() {
        return new WarehouseTransaction()
            .id(longCount.incrementAndGet())
            .imovUid(UUID.randomUUID().toString())
            .phaseNo(intCount.incrementAndGet())
            .entryType(UUID.randomUUID().toString())
            .quantity(intCount.incrementAndGet())
            .notes(UUID.randomUUID().toString())
            .personName(UUID.randomUUID().toString())
            .workDayId(intCount.incrementAndGet())
            .submissionId(longCount.incrementAndGet())
            .submissionUuid(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
