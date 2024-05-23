package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AssignmentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Assignment getAssignmentSample1() {
        return new Assignment()
            .id(1L)
            .uid("uid1")
            .phaseNo(1)
            .code("code1")
            .districtCode(1)
            .gov("gov1")
            .district("district1")
            .subdistrict("subdistrict1")
            .village("village1")
            .subvillage("subvillage1")
            .name("name1")
            .dayId(1)
            .itnsPlanned(1)
            .targetType(1)
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static Assignment getAssignmentSample2() {
        return new Assignment()
            .id(2L)
            .uid("uid2")
            .phaseNo(2)
            .code("code2")
            .districtCode(2)
            .gov("gov2")
            .district("district2")
            .subdistrict("subdistrict2")
            .village("village2")
            .subvillage("subvillage2")
            .name("name2")
            .dayId(2)
            .itnsPlanned(2)
            .targetType(2)
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static Assignment getAssignmentRandomSampleGenerator() {
        return new Assignment()
            .id(longCount.incrementAndGet())
            .uid(UUID.randomUUID().toString())
            .phaseNo(intCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .districtCode(intCount.incrementAndGet())
            .gov(UUID.randomUUID().toString())
            .district(UUID.randomUUID().toString())
            .subdistrict(UUID.randomUUID().toString())
            .village(UUID.randomUUID().toString())
            .subvillage(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .dayId(intCount.incrementAndGet())
            .itnsPlanned(intCount.incrementAndGet())
            .targetType(intCount.incrementAndGet())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
