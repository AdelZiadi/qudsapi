package org.nmcpye.datarun.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.nmcpye.datarun.domain.WarehouseItemTestSamples.*;

import org.junit.jupiter.api.Test;
import org.nmcpye.datarun.web.rest.TestUtil;

class WarehouseItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WarehouseItem.class);
        WarehouseItem warehouseItem1 = getWarehouseItemSample1();
        WarehouseItem warehouseItem2 = new WarehouseItem();
        assertThat(warehouseItem1).isNotEqualTo(warehouseItem2);

        warehouseItem2.setId(warehouseItem1.getId());
        assertThat(warehouseItem1).isEqualTo(warehouseItem2);

        warehouseItem2 = getWarehouseItemSample2();
        assertThat(warehouseItem1).isNotEqualTo(warehouseItem2);
    }
}
