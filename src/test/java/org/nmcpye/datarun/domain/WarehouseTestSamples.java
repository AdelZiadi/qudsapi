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
            .uid("uid1")
            .name("name1")
            .description("description1")
            .gpsCoordinate("gpsCoordinate1")
            .supervisor("supervisor1")
            .code("code1")
            .supervisorMobile("supervisorMobile1");
    }

    public static Warehouse getWarehouseSample2() {
        return new Warehouse()
            .id(2L)
            .uid("uid2")
            .name("name2")
            .description("description2")
            .gpsCoordinate("gpsCoordinate2")
            .supervisor("supervisor2")
            .code("code2")
            .supervisorMobile("supervisorMobile2");
    }

    public static Warehouse getWarehouseRandomSampleGenerator() {
        return new Warehouse()
            .id(longCount.incrementAndGet())
            .uid(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .gpsCoordinate(UUID.randomUUID().toString())
            .supervisor(UUID.randomUUID().toString())
            .code(UUID.randomUUID().toString())
            .supervisorMobile(UUID.randomUUID().toString());
    }
}
