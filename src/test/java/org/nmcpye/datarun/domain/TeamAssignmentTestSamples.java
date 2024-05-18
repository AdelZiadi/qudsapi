package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TeamAssignmentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static TeamAssignment getTeamAssignmentSample1() {
        return new TeamAssignment()
            .id(1L)
            .phaseNo(1)
            .fieldCode(1L)
            .districtCode(1)
            .gov("gov1")
            .district("district1")
            .subdistrict("subdistrict1")
            .village("village1")
            .subvillage("subvillage1")
            .ppdName("ppdName1")
            .dayId(1)
            .itnsPlanned(1)
            .targetType(1)
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static TeamAssignment getTeamAssignmentSample2() {
        return new TeamAssignment()
            .id(2L)
            .phaseNo(2)
            .fieldCode(2L)
            .districtCode(2)
            .gov("gov2")
            .district("district2")
            .subdistrict("subdistrict2")
            .village("village2")
            .subvillage("subvillage2")
            .ppdName("ppdName2")
            .dayId(2)
            .itnsPlanned(2)
            .targetType(2)
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static TeamAssignment getTeamAssignmentRandomSampleGenerator() {
        return new TeamAssignment()
            .id(longCount.incrementAndGet())
            .phaseNo(intCount.incrementAndGet())
            .fieldCode(longCount.incrementAndGet())
            .districtCode(intCount.incrementAndGet())
            .gov(UUID.randomUUID().toString())
            .district(UUID.randomUUID().toString())
            .subdistrict(UUID.randomUUID().toString())
            .village(UUID.randomUUID().toString())
            .subvillage(UUID.randomUUID().toString())
            .ppdName(UUID.randomUUID().toString())
            .dayId(intCount.incrementAndGet())
            .itnsPlanned(intCount.incrementAndGet())
            .targetType(intCount.incrementAndGet())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
