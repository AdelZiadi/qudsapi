package org.nmcpye.datarun.domain.common;

public interface IdentifiableObject<ID extends Number> {

    void setId(ID id);

    ID getId();

    /**
     * @return external unique ID of the object as used in the RESTful API
     */
    String getUid();

    void setUid(String uid);

    IdentifiableObject setIsPersisted();

}
