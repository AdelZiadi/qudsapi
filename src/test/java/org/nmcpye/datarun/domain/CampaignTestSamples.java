package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CampaignTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Campaign getCampaignSample1() {
        return new Campaign().id(1L).campaignCode("campaignCode1").campaignDays(1).campaignYear(1).campaignNote("campaignNote1");
    }

    public static Campaign getCampaignSample2() {
        return new Campaign().id(2L).campaignCode("campaignCode2").campaignDays(2).campaignYear(2).campaignNote("campaignNote2");
    }

    public static Campaign getCampaignRandomSampleGenerator() {
        return new Campaign()
            .id(longCount.incrementAndGet())
            .campaignCode(UUID.randomUUID().toString())
            .campaignDays(intCount.incrementAndGet())
            .campaignYear(intCount.incrementAndGet())
            .campaignNote(UUID.randomUUID().toString());
    }
}
