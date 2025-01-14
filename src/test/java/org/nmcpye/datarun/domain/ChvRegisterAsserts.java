package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class ChvRegisterAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertChvRegisterAllPropertiesEquals(ChvRegister expected, ChvRegister actual) {
        assertChvRegisterAutoGeneratedPropertiesEquals(expected, actual);
        assertChvRegisterAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertChvRegisterAllUpdatablePropertiesEquals(ChvRegister expected, ChvRegister actual) {
        assertChvRegisterUpdatableFieldsEquals(expected, actual);
        assertChvRegisterUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertChvRegisterAutoGeneratedPropertiesEquals(ChvRegister expected, ChvRegister actual) {
        assertThat(expected)
            .as("Verify ChvRegister auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()))
            .satisfies(e -> assertThat(e.getCreatedBy()).as("check createdBy").isEqualTo(actual.getCreatedBy()))
            .satisfies(e -> assertThat(e.getCreatedDate()).as("check createdDate").isEqualTo(actual.getCreatedDate()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertChvRegisterUpdatableFieldsEquals(ChvRegister expected, ChvRegister actual) {
        assertThat(expected)
            .as("Verify ChvRegister relevant properties")
            .satisfies(e -> assertThat(e.getUid()).as("check uid").isEqualTo(actual.getUid()))
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getLocationName()).as("check locationName").isEqualTo(actual.getLocationName()))
            .satisfies(e -> assertThat(e.getAge()).as("check age").isEqualTo(actual.getAge()))
            .satisfies(e -> assertThat(e.getGender()).as("check gender").isEqualTo(actual.getGender()))
            .satisfies(e -> assertThat(e.getVisitDate()).as("check visitDate").isEqualTo(actual.getVisitDate()))
            .satisfies(e -> assertThat(e.getPregnant()).as("check pregnant").isEqualTo(actual.getPregnant()))
            .satisfies(e -> assertThat(e.getTestResult()).as("check testResult").isEqualTo(actual.getTestResult()))
            .satisfies(e -> assertThat(e.getDetectionType()).as("check detectionType").isEqualTo(actual.getDetectionType()))
            .satisfies(e -> assertThat(e.getSeverity()).as("check severity").isEqualTo(actual.getSeverity()))
            .satisfies(e -> assertThat(e.getTreatment()).as("check treatment").isEqualTo(actual.getTreatment()))
            .satisfies(e -> assertThat(e.getDeleted()).as("check deleted").isEqualTo(actual.getDeleted()))
            .satisfies(e -> assertThat(e.getStartEntryTime()).as("check startEntryTime").isEqualTo(actual.getStartEntryTime()))
            .satisfies(e -> assertThat(e.getFinishedEntryTime()).as("check finishedEntryTime").isEqualTo(actual.getFinishedEntryTime()))
            .satisfies(e -> assertThat(e.getComment()).as("check comment").isEqualTo(actual.getComment()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertChvRegisterUpdatableRelationshipsEquals(ChvRegister expected, ChvRegister actual) {
        assertThat(expected)
            .as("Verify ChvRegister relationships")
            .satisfies(e -> assertThat(e.getLocation()).as("check location").isEqualTo(actual.getLocation()))
            .satisfies(e -> assertThat(e.getActivity()).as("check activity").isEqualTo(actual.getActivity()))
            .satisfies(e -> assertThat(e.getTeam()).as("check team").isEqualTo(actual.getTeam()));
    }
}
