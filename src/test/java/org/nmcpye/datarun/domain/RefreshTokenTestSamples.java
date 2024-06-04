package org.nmcpye.datarun.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RefreshTokenTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static RefreshToken getRefreshTokenSample1() {
        return new RefreshToken().id(1L).token("token1");
    }

    public static RefreshToken getRefreshTokenSample2() {
        return new RefreshToken().id(2L).token("token2");
    }

    public static RefreshToken getRefreshTokenRandomSampleGenerator() {
        return new RefreshToken().id(longCount.incrementAndGet()).token(UUID.randomUUID().toString());
    }
}
