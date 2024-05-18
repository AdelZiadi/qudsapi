package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MVillagesLocationsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static MVillagesLocations getMVillagesLocationsSample1() {
        return new MVillagesLocations()
            .id(1L)
            .ppcCode(1L)
            .mappingStatus(1)
            .districtCode(1)
            .villageUid("villageUid1")
            .subdistrictName("subdistrictName1")
            .villageName("villageName1")
            .subvillageName("subvillageName1")
            .ppdName("ppdName1")
            .urbanRuralId(1)
            .urbanRural("urbanRural1")
            .settlement("settlement1")
            .ppcCodeGis(1L);
    }

    public static MVillagesLocations getMVillagesLocationsSample2() {
        return new MVillagesLocations()
            .id(2L)
            .ppcCode(2L)
            .mappingStatus(2)
            .districtCode(2)
            .villageUid("villageUid2")
            .subdistrictName("subdistrictName2")
            .villageName("villageName2")
            .subvillageName("subvillageName2")
            .ppdName("ppdName2")
            .urbanRuralId(2)
            .urbanRural("urbanRural2")
            .settlement("settlement2")
            .ppcCodeGis(2L);
    }

    public static MVillagesLocations getMVillagesLocationsRandomSampleGenerator() {
        return new MVillagesLocations()
            .id(longCount.incrementAndGet())
            .ppcCode(longCount.incrementAndGet())
            .mappingStatus(intCount.incrementAndGet())
            .districtCode(intCount.incrementAndGet())
            .villageUid(UUID.randomUUID().toString())
            .subdistrictName(UUID.randomUUID().toString())
            .villageName(UUID.randomUUID().toString())
            .subvillageName(UUID.randomUUID().toString())
            .ppdName(UUID.randomUUID().toString())
            .urbanRuralId(intCount.incrementAndGet())
            .urbanRural(UUID.randomUUID().toString())
            .settlement(UUID.randomUUID().toString())
            .ppcCodeGis(longCount.incrementAndGet());
    }
}
