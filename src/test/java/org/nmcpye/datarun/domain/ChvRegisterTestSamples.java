package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ChvRegisterTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ChvRegister getChvRegisterSample1() {
        return new ChvRegister()
            .id(1L)
            .uid("uid1")
            .code("code1")
            .name("name1")
            .locationName("locationName1")
            .age(1)
            .comment("comment1")
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static ChvRegister getChvRegisterSample2() {
        return new ChvRegister()
            .id(2L)
            .uid("uid2")
            .code("code2")
            .name("name2")
            .locationName("locationName2")
            .age(2)
            .comment("comment2")
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static ChvRegister getChvRegisterRandomSampleGenerator() {
        return new ChvRegister()
            .id(longCount.incrementAndGet())
            .uid(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .locationName(UUID.randomUUID().toString())
            .age(intCount.incrementAndGet())
            .comment(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
