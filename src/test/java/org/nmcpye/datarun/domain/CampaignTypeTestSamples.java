package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CampaignTypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CampaignType getCampaignTypeSample1() {
        return new CampaignType().id(1L).campaignType("campaignType1").description("description1");
    }

    public static CampaignType getCampaignTypeSample2() {
        return new CampaignType().id(2L).campaignType("campaignType2").description("description2");
    }

    public static CampaignType getCampaignTypeRandomSampleGenerator() {
        return new CampaignType()
            .id(longCount.incrementAndGet())
            .campaignType(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
