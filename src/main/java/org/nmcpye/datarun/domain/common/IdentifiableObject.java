package org.nmcpye.datarun.domain.common;

public interface IdentifiableObject {
    /**
     * @return external unique ID of the object as used in the RESTful API
     */
    String getUid();

    void setUid(String uid);
}
