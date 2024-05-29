package org.nmcpye.datarun.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nmcpye.datarun.domain.enumeration.PublicLocationType;
import org.springframework.data.domain.Persistable;

/**
 * A VillageLocation.
 */
@Entity
@Table(name = "village_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VillageLocation extends AbstractAuditingEntity<Long> implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 11)
    @Column(name = "uid", length = 11, unique = true)
    private String uid;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "mapping_status")
    private Integer mappingStatus;

    @Column(name = "district_code")
    private Integer districtCode;

    @Column(name = "village_uid")
    private String villageUid;

    @Column(name = "subdistrict_name")
    private String subdistrictName;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "subvillage_name")
    private String subvillageName;

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
    private String ppcCodeGis;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private PublicLocationType level;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition
    @Transient
    private boolean isPersisted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VillageLocation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public VillageLocation uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return this.code;
    }

    public VillageLocation code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public VillageLocation name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMappingStatus() {
        return this.mappingStatus;
    }

    public VillageLocation mappingStatus(Integer mappingStatus) {
        this.setMappingStatus(mappingStatus);
        return this;
    }

    public void setMappingStatus(Integer mappingStatus) {
        this.mappingStatus = mappingStatus;
    }

    public Integer getDistrictCode() {
        return this.districtCode;
    }

    public VillageLocation districtCode(Integer districtCode) {
        this.setDistrictCode(districtCode);
        return this;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getVillageUid() {
        return this.villageUid;
    }

    public VillageLocation villageUid(String villageUid) {
        this.setVillageUid(villageUid);
        return this;
    }

    public void setVillageUid(String villageUid) {
        this.villageUid = villageUid;
    }

    public String getSubdistrictName() {
        return this.subdistrictName;
    }

    public VillageLocation subdistrictName(String subdistrictName) {
        this.setSubdistrictName(subdistrictName);
        return this;
    }

    public void setSubdistrictName(String subdistrictName) {
        this.subdistrictName = subdistrictName;
    }

    public String getVillageName() {
        return this.villageName;
    }

    public VillageLocation villageName(String villageName) {
        this.setVillageName(villageName);
        return this;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getSubvillageName() {
        return this.subvillageName;
    }

    public VillageLocation subvillageName(String subvillageName) {
        this.setSubvillageName(subvillageName);
        return this;
    }

    public void setSubvillageName(String subvillageName) {
        this.subvillageName = subvillageName;
    }

    public Integer getUrbanRuralId() {
        return this.urbanRuralId;
    }

    public VillageLocation urbanRuralId(Integer urbanRuralId) {
        this.setUrbanRuralId(urbanRuralId);
        return this;
    }

    public void setUrbanRuralId(Integer urbanRuralId) {
        this.urbanRuralId = urbanRuralId;
    }

    public String getUrbanRural() {
        return this.urbanRural;
    }

    public VillageLocation urbanRural(String urbanRural) {
        this.setUrbanRural(urbanRural);
        return this;
    }

    public void setUrbanRural(String urbanRural) {
        this.urbanRural = urbanRural;
    }

    public String getSettlement() {
        return this.settlement;
    }

    public VillageLocation settlement(String settlement) {
        this.setSettlement(settlement);
        return this;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public Double getPop2004() {
        return this.pop2004;
    }

    public VillageLocation pop2004(Double pop2004) {
        this.setPop2004(pop2004);
        return this;
    }

    public void setPop2004(Double pop2004) {
        this.pop2004 = pop2004;
    }

    public Double getPop2022() {
        return this.pop2022;
    }

    public VillageLocation pop2022(Double pop2022) {
        this.setPop2022(pop2022);
        return this;
    }

    public void setPop2022(Double pop2022) {
        this.pop2022 = pop2022;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public VillageLocation longitude(Double longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public VillageLocation latitude(Double latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPpcCodeGis() {
        return this.ppcCodeGis;
    }

    public VillageLocation ppcCodeGis(String ppcCodeGis) {
        this.setPpcCodeGis(ppcCodeGis);
        return this;
    }

    public void setPpcCodeGis(String ppcCodeGis) {
        this.ppcCodeGis = ppcCodeGis;
    }

    public PublicLocationType getLevel() {
        return this.level;
    }

    public VillageLocation level(PublicLocationType level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(PublicLocationType level) {
        this.level = level;
    }

    // Inherited createdBy methods
    public VillageLocation createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public VillageLocation createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public VillageLocation lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public VillageLocation lastModifiedDate(Instant lastModifiedDate) {
        this.setLastModifiedDate(lastModifiedDate);
        return this;
    }

    @PostLoad
    @PostPersist
    public void updateEntityState() {
        this.setIsPersisted();
    }

    @Transient
    @Override
    public boolean isNew() {
        return !this.isPersisted;
    }

    public VillageLocation setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VillageLocation)) {
            return false;
        }
        return getId() != null && getId().equals(((VillageLocation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VillageLocation{" +
            "id=" + getId() +
            ", uid='" + getUid() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", mappingStatus=" + getMappingStatus() +
            ", districtCode=" + getDistrictCode() +
            ", villageUid='" + getVillageUid() + "'" +
            ", subdistrictName='" + getSubdistrictName() + "'" +
            ", villageName='" + getVillageName() + "'" +
            ", subvillageName='" + getSubvillageName() + "'" +
            ", urbanRuralId=" + getUrbanRuralId() +
            ", urbanRural='" + getUrbanRural() + "'" +
            ", settlement='" + getSettlement() + "'" +
            ", pop2004=" + getPop2004() +
            ", pop2022=" + getPop2022() +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", ppcCodeGis='" + getPpcCodeGis() + "'" +
            ", level='" + getLevel() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
