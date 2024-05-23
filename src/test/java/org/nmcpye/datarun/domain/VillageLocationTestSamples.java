package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VillageLocationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static VillageLocation getVillageLocationSample1() {
        return new VillageLocation()
            .id(1L)
            .uid("uid1")
            .code("code1")
            .mappingStatus(1)
            .districtCode(1)
            .villageUid("villageUid1")
            .subdistrictName("subdistrictName1")
            .villageName("villageName1")
            .subvillageName("subvillageName1")
            .name("name1")
            .urbanRuralId(1)
            .urbanRural("urbanRural1")
            .settlement("settlement1")
            .ppcCodeGis("ppcCodeGis1");
    }

    public static VillageLocation getVillageLocationSample2() {
        return new VillageLocation()
            .id(2L)
            .uid("uid2")
            .code("code2")
            .mappingStatus(2)
            .districtCode(2)
            .villageUid("villageUid2")
            .subdistrictName("subdistrictName2")
            .villageName("villageName2")
            .subvillageName("subvillageName2")
            .name("name2")
            .urbanRuralId(2)
            .urbanRural("urbanRural2")
            .settlement("settlement2")
            .ppcCodeGis("ppcCodeGis2");
    }

    public static VillageLocation getVillageLocationRandomSampleGenerator() {
        return new VillageLocation()
            .id(longCount.incrementAndGet())
            .uid(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .mappingStatus(intCount.incrementAndGet())
            .districtCode(intCount.incrementAndGet())
            .villageUid(UUID.randomUUID().toString())
            .subdistrictName(UUID.randomUUID().toString())
            .villageName(UUID.randomUUID().toString())
            .subvillageName(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .urbanRuralId(intCount.incrementAndGet())
            .urbanRural(UUID.randomUUID().toString())
            .settlement(UUID.randomUUID().toString())
            .ppcCodeGis(UUID.randomUUID().toString());
    }
}
