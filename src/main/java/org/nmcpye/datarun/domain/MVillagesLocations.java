package org.nmcpye.datarun.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nmcpye.datarun.domain.enumeration.PublicLocationType;

/**
 * A MVillagesLocations.
 */
@Entity
@Table(name = "m_villages_locations")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MVillagesLocations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ppc_code", nullable = false)
    private Long ppcCode;

    @Column(name = "mapping_status")
    private Integer mappingStatus;

    @NotNull
    @Column(name = "district_code", nullable = false)
    private Integer districtCode;

    @Column(name = "village_uid")
    private String villageUid;

    @Column(name = "subdistrict_name")
    private String subdistrictName;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "subvillage_name")
    private String subvillageName;

    @Column(name = "ppd_name")
    private String ppdName;

    @Column(name = "urban_rural_id")
    private Integer urbanRuralId;

    @Column(name = "urban_rural")
    private String urbanRural;

    @Column(name = "settlement")
    private String settlement;

    @Column(name = "pop_2004")
    private Double pop2004;

    @Column(name = "pop_2022")
    private Double pop2022;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "ppc_code_gis", nullable = false)
    private Long ppcCodeGis;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private PublicLocationType level;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MVillagesLocations id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPpcCode() {
        return this.ppcCode;
    }

    public MVillagesLocations ppcCode(Long ppcCode) {
        this.setPpcCode(ppcCode);
        return this;
    }

    public void setPpcCode(Long ppcCode) {
        this.ppcCode = ppcCode;
    }

    public Integer getMappingStatus() {
        return this.mappingStatus;
    }

    public MVillagesLocations mappingStatus(Integer mappingStatus) {
        this.setMappingStatus(mappingStatus);
        return this;
    }

    public void setMappingStatus(Integer mappingStatus) {
        this.mappingStatus = mappingStatus;
    }

    public Integer getDistrictCode() {
        return this.districtCode;
    }

    public MVillagesLocations districtCode(Integer districtCode) {
        this.setDistrictCode(districtCode);
        return this;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getVillageUid() {
        return this.villageUid;
    }

    public MVillagesLocations villageUid(String villageUid) {
        this.setVillageUid(villageUid);
        return this;
    }

    public void setVillageUid(String villageUid) {
        this.villageUid = villageUid;
    }

    public String getSubdistrictName() {
        return this.subdistrictName;
    }

    public MVillagesLocations subdistrictName(String subdistrictName) {
        this.setSubdistrictName(subdistrictName);
        return this;
    }

    public void setSubdistrictName(String subdistrictName) {
        this.subdistrictName = subdistrictName;
    }

    public String getVillageName() {
        return this.villageName;
    }

    public MVillagesLocations villageName(String villageName) {
        this.setVillageName(villageName);
        return this;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getSubvillageName() {
        return this.subvillageName;
    }

    public MVillagesLocations subvillageName(String subvillageName) {
        this.setSubvillageName(subvillageName);
        return this;
    }

    public void setSubvillageName(String subvillageName) {
        this.subvillageName = subvillageName;
    }

    public String getPpdName() {
        return this.ppdName;
    }

    public MVillagesLocations ppdName(String ppdName) {
        this.setPpdName(ppdName);
        return this;
    }

    public void setPpdName(String ppdName) {
        this.ppdName = ppdName;
    }

    public Integer getUrbanRuralId() {
        return this.urbanRuralId;
    }

    public MVillagesLocations urbanRuralId(Integer urbanRuralId) {
        this.setUrbanRuralId(urbanRuralId);
        return this;
    }

    public void setUrbanRuralId(Integer urbanRuralId) {
        this.urbanRuralId = urbanRuralId;
    }

    public String getUrbanRural() {
        return this.urbanRural;
    }

    public MVillagesLocations urbanRural(String urbanRural) {
        this.setUrbanRural(urbanRural);
        return this;
    }

    public void setUrbanRural(String urbanRural) {
        this.urbanRural = urbanRural;
    }

    public String getSettlement() {
        return this.settlement;
    }

    public MVillagesLocations settlement(String settlement) {
        this.setSettlement(settlement);
        return this;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public Double getPop2004() {
        return this.pop2004;
    }

    public MVillagesLocations pop2004(Double pop2004) {
        this.setPop2004(pop2004);
        return this;
    }

    public void setPop2004(Double pop2004) {
        this.pop2004 = pop2004;
    }

    public Double getPop2022() {
        return this.pop2022;
    }

    public MVillagesLocations pop2022(Double pop2022) {
        this.setPop2022(pop2022);
        return this;
    }

    public void setPop2022(Double pop2022) {
        this.pop2022 = pop2022;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public MVillagesLocations longitude(Double longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public MVillagesLocations latitude(Double latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getPpcCodeGis() {
        return this.ppcCodeGis;
    }

    public MVillagesLocations ppcCodeGis(Long ppcCodeGis) {
        this.setPpcCodeGis(ppcCodeGis);
        return this;
    }

    public void setPpcCodeGis(Long ppcCodeGis) {
        this.ppcCodeGis = ppcCodeGis;
    }

    public PublicLocationType getLevel() {
        return this.level;
    }

    public MVillagesLocations level(PublicLocationType level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(PublicLocationType level) {
        this.level = level;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MVillagesLocations)) {
            return false;
        }
        return getId() != null && getId().equals(((MVillagesLocations) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MVillagesLocations{" +
            "id=" + getId() +
            ", ppcCode=" + getPpcCode() +
            ", mappingStatus=" + getMappingStatus() +
            ", districtCode=" + getDistrictCode() +
            ", villageUid='" + getVillageUid() + "'" +
            ", subdistrictName='" + getSubdistrictName() + "'" +
            ", villageName='" + getVillageName() + "'" +
            ", subvillageName='" + getSubvillageName() + "'" +
            ", ppdName='" + getPpdName() + "'" +
            ", urbanRuralId=" + getUrbanRuralId() +
            ", urbanRural='" + getUrbanRural() + "'" +
            ", settlement='" + getSettlement() + "'" +
            ", pop2004=" + getPop2004() +
            ", pop2022=" + getPop2022() +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", ppcCodeGis=" + getPpcCodeGis() +
            ", level='" + getLevel() + "'" +
            "}";
    }
}
