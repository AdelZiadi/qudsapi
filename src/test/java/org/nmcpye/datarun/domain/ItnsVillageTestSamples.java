package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ItnsVillageTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static ItnsVillage getItnsVillageSample1() {
        return new ItnsVillage()
            .id(1L)
            .submissionUuid("submissionUuid1")
            .submissionId(1L)
            .otherReasonComment("otherReasonComment1")
            .reasonNotcomplete("reasonNotcomplete1")
            .settlementName("settlementName1")
            .tlCommenet("tlCommenet1")
            .timeSpentHours(1)
            .timeSpentMinutes(1)
            .difficulties("difficulties1")
            .locationCaptured("locationCaptured1")
            .hoProof("hoProof1")
            .hoProofUrl("hoProofUrl1")
            .untargetingOtherSpecify("untargetingOtherSpecify1")
            .otherVillageName("otherVillageName1")
            .otherVillageCode("otherVillageCode1")
            .otherTeamNo(1L)
            .createdBy("createdBy1")
            .lastModifiedBy("lastModifiedBy1");
    }

    public static ItnsVillage getItnsVillageSample2() {
        return new ItnsVillage()
            .id(2L)
            .submissionUuid("submissionUuid2")
            .submissionId(2L)
            .otherReasonComment("otherReasonComment2")
            .reasonNotcomplete("reasonNotcomplete2")
            .settlementName("settlementName2")
            .tlCommenet("tlCommenet2")
            .timeSpentHours(2)
            .timeSpentMinutes(2)
            .difficulties("difficulties2")
            .locationCaptured("locationCaptured2")
            .hoProof("hoProof2")
            .hoProofUrl("hoProofUrl2")
            .untargetingOtherSpecify("untargetingOtherSpecify2")
            .otherVillageName("otherVillageName2")
            .otherVillageCode("otherVillageCode2")
            .otherTeamNo(2L)
            .createdBy("createdBy2")
            .lastModifiedBy("lastModifiedBy2");
    }

    public static ItnsVillage getItnsVillageRandomSampleGenerator() {
        return new ItnsVillage()
            .id(longCount.incrementAndGet())
            .submissionUuid(UUID.randomUUID().toString())
            .submissionId(longCount.incrementAndGet())
            .otherReasonComment(UUID.randomUUID().toString())
            .reasonNotcomplete(UUID.randomUUID().toString())
            .settlementName(UUID.randomUUID().toString())
            .tlCommenet(UUID.randomUUID().toString())
            .timeSpentHours(intCount.incrementAndGet())
            .timeSpentMinutes(intCount.incrementAndGet())
            .difficulties(UUID.randomUUID().toString())
            .locationCaptured(UUID.randomUUID().toString())
            .hoProof(UUID.randomUUID().toString())
            .hoProofUrl(UUID.randomUUID().toString())
            .untargetingOtherSpecify(UUID.randomUUID().toString())
            .otherVillageName(UUID.randomUUID().toString())
            .otherVillageCode(UUID.randomUUID().toString())
            .otherTeamNo(longCount.incrementAndGet())
            .createdBy(UUID.randomUUID().toString())
            .lastModifiedBy(UUID.randomUUID().toString());
    }
}
