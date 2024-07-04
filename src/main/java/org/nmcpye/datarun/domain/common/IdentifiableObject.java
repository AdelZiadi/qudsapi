package org.nmcpye.datarun.domain.common;

import org.nmcpye.datarun.domain.ItnsVillage;

public interface IdentifiableObject {
    /**
     * @return external unique ID of the object as used in the RESTful API
     */
    String getUid();

    void setUid(String uid);

    IdentifiableObject setIsPersisted();

}
