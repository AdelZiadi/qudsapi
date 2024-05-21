package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.ActivityTestSamples.*;
import static org.nmcpye.datarun.domain.TeamTestSamples.*;
import static org.nmcpye.datarun.domain.WarehouseItemTestSamples.*;
import static org.nmcpye.datarun.domain.WarehouseTestSamples.*;
import static org.nmcpye.datarun.domain.WarehouseTransactionTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class WarehouseTransactionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WarehouseTransaction.class);
        WarehouseTransaction warehouseTransaction1 = getWarehouseTransactionSample1();
        WarehouseTransaction warehouseTransaction2 = new WarehouseTransaction();
        assertThat(warehouseTransaction1).isNotEqualTo(warehouseTransaction2);

        warehouseTransaction2.setId(warehouseTransaction1.getId());
        assertThat(warehouseTransaction1).isEqualTo(warehouseTransaction2);

        warehouseTransaction2 = getWarehouseTransactionSample2();
        assertThat(warehouseTransaction1).isNotEqualTo(warehouseTransaction2);
    }

    @Test
    void itemTest() throws Exception {
        WarehouseTransaction warehouseTransaction = getWarehouseTransactionRandomSampleGenerator();
        WarehouseItem warehouseItemBack = getWarehouseItemRandomSampleGenerator();

        warehouseTransaction.setItem(warehouseItemBack);
        assertThat(warehouseTransaction.getItem()).isEqualTo(warehouseItemBack);

        warehouseTransaction.item(null);
        assertThat(warehouseTransaction.getItem()).isNull();
    }

    @Test
    void sourceWarehouseTest() throws Exception {
        WarehouseTransaction warehouseTransaction = getWarehouseTransactionRandomSampleGenerator();
        Warehouse warehouseBack = getWarehouseRandomSampleGenerator();

        warehouseTransaction.setSourceWarehouse(warehouseBack);
        assertThat(warehouseTransaction.getSourceWarehouse()).isEqualTo(warehouseBack);

        warehouseTransaction.sourceWarehouse(null);
        assertThat(warehouseTransaction.getSourceWarehouse()).isNull();
    }

    @Test
    void teamTest() throws Exception {
        WarehouseTransaction warehouseTransaction = getWarehouseTransactionRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        warehouseTransaction.setTeam(teamBack);
        assertThat(warehouseTransaction.getTeam()).isEqualTo(teamBack);

        warehouseTransaction.team(null);
        assertThat(warehouseTransaction.getTeam()).isNull();
    }

    @Test
    void warehouseTest() throws Exception {
        WarehouseTransaction warehouseTransaction = getWarehouseTransactionRandomSampleGenerator();
        Warehouse warehouseBack = getWarehouseRandomSampleGenerator();

        warehouseTransaction.setWarehouse(warehouseBack);
        assertThat(warehouseTransaction.getWarehouse()).isEqualTo(warehouseBack);

        warehouseTransaction.warehouse(null);
        assertThat(warehouseTransaction.getWarehouse()).isNull();
    }

    @Test
    void activityTest() throws Exception {
        WarehouseTransaction warehouseTransaction = getWarehouseTransactionRandomSampleGenerator();
        Activity activityBack = getActivityRandomSampleGenerator();

        warehouseTransaction.setActivity(activityBack);
        assertThat(warehouseTransaction.getActivity()).isEqualTo(activityBack);

        warehouseTransaction.activity(null);
        assertThat(warehouseTransaction.getActivity()).isNull();
    }
}
