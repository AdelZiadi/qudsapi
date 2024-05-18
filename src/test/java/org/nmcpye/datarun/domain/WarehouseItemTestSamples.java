package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class WarehouseItemTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static WarehouseItem getWarehouseItemSample1() {
        return new WarehouseItem().id(1L).itemName("itemName1").itemDescription("itemDescription1");
    }

    public static WarehouseItem getWarehouseItemSample2() {
        return new WarehouseItem().id(2L).itemName("itemName2").itemDescription("itemDescription2");
    }

    public static WarehouseItem getWarehouseItemRandomSampleGenerator() {
        return new WarehouseItem()
            .id(longCount.incrementAndGet())
            .itemName(UUID.randomUUID().toString())
            .itemDescription(UUID.randomUUID().toString());
    }
}
