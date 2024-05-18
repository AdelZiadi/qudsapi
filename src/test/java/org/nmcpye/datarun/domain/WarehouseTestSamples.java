package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class WarehouseTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Warehouse getWarehouseSample1() {
        return new Warehouse()
            .id(1L)
            .whName("whName1")
            .description("description1")
            .gpsCoordinate("gpsCoordinate1")
            .supervisorName("supervisorName1")
            .whNo(1L)
            .supervisorMobile("supervisorMobile1")
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static Warehouse getWarehouseSample2() {
        return new Warehouse()
            .id(2L)
            .whName("whName2")
            .description("description2")
            .gpsCoordinate("gpsCoordinate2")
            .supervisorName("supervisorName2")
            .whNo(2L)
            .supervisorMobile("supervisorMobile2")
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static Warehouse getWarehouseRandomSampleGenerator() {
        return new Warehouse()
            .id(longCount.incrementAndGet())
            .whName(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .gpsCoordinate(UUID.randomUUID().toString())
            .supervisorName(UUID.randomUUID().toString())
            .whNo(longCount.incrementAndGet())
            .supervisorMobile(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
