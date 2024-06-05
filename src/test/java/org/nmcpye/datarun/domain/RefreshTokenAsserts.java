package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class RefreshTokenAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRefreshTokenAllPropertiesEquals(RefreshToken expected, RefreshToken actual) {
        assertRefreshTokenAutoGeneratedPropertiesEquals(expected, actual);
        assertRefreshTokenAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRefreshTokenAllUpdatablePropertiesEquals(RefreshToken expected, RefreshToken actual) {
        assertRefreshTokenUpdatableFieldsEquals(expected, actual);
        assertRefreshTokenUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRefreshTokenAutoGeneratedPropertiesEquals(RefreshToken expected, RefreshToken actual) {
        assertThat(expected)
            .as("Verify RefreshToken auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRefreshTokenUpdatableFieldsEquals(RefreshToken expected, RefreshToken actual) {
        assertThat(expected)
            .as("Verify RefreshToken relevant properties")
            .satisfies(e -> assertThat(e.getToken()).as("check token").isEqualTo(actual.getToken()))
            .satisfies(e -> assertThat(e.getExpiryDate()).as("check expiryDate").isEqualTo(actual.getExpiryDate()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertRefreshTokenUpdatableRelationshipsEquals(RefreshToken expected, RefreshToken actual) {}
}