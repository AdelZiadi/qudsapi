package org.nmcpye.datarun.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A TeamAssignment.
 */
@Entity
@Table(name = "team_assignment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TeamAssignment extends AbstractAuditingEntity<Long> implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "phase_no")
    private Integer phaseNo;

    @Column(name = "field_code")
    private Long fieldCode;

    @Column(name = "district_code")
    private Integer districtCode;

    @Column(name = "gov")
    private String gov;

    @Column(name = "district")
    private String district;

    @Column(name = "subdistrict")
    private String subdistrict;

    @Column(name = "village")
    private String village;

    @Column(name = "subvillage")
    private String subvillage;

    @Column(name = "name")
    private String name;

    @Column(name = "day_id")
    private Integer dayId;

    @Column(name = "population")
    private Double population;

    @Column(name = "itns_planned")
    private Integer itnsPlanned;

    @Column(name = "target_type")
    private Integer targetType;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "start_day_date")
    private Instant startDayDate;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition
    @Transient
    private boolean isPersisted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "campaignType" }, allowSetters = true)
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    private MVillagesLocations location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "campaign", "operationRoom", "warehouse", "userInfo" }, allowSetters = true)
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "campaign" }, allowSetters = true)
    private Warehouse warehouse;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TeamAssignment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhaseNo() {
        return this.phaseNo;
    }

    public TeamAssignment phaseNo(Integer phaseNo) {
        this.setPhaseNo(phaseNo);
        return this;
    }

    public void setPhaseNo(Integer phaseNo) {
        this.phaseNo = phaseNo;
    }

    public Long getFieldCode() {
        return this.fieldCode;
    }

    public TeamAssignment fieldCode(Long fieldCode) {
        this.setFieldCode(fieldCode);
        return this;
    }

    public void setFieldCode(Long fieldCode) {
        this.fieldCode = fieldCode;
    }

    public Integer getDistrictCode() {
        return this.districtCode;
    }

    public TeamAssignment districtCode(Integer districtCode) {
        this.setDistrictCode(districtCode);
        return this;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getGov() {
        return this.gov;
    }

    public TeamAssignment gov(String gov) {
        this.setGov(gov);
        return this;
    }

    public void setGov(String gov) {
        this.gov = gov;
    }

    public String getDistrict() {
        return this.district;
    }

    public TeamAssignment district(String district) {
        this.setDistrict(district);
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubdistrict() {
        return this.subdistrict;
    }

    public TeamAssignment subdistrict(String subdistrict) {
        this.setSubdistrict(subdistrict);
        return this;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getVillage() {
        return this.village;
    }

    public TeamAssignment village(String village) {
        this.setVillage(village);
        return this;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getSubvillage() {
        return this.subvillage;
    }

    public TeamAssignment subvillage(String subvillage) {
        this.setSubvillage(subvillage);
        return this;
    }

    public void setSubvillage(String subvillage) {
        this.subvillage = subvillage;
    }

    public String getName() {
        return this.name;
    }

    public TeamAssignment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDayId() {
        return this.dayId;
    }

    public TeamAssignment dayId(Integer dayId) {
        this.setDayId(dayId);
        return this;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public Double getPopulation() {
        return this.population;
    }

    public TeamAssignment population(Double population) {
        this.setPopulation(population);
        return this;
    }

    public void setPopulation(Double population) {
        this.population = population;
    }

    public Integer getItnsPlanned() {
        return this.itnsPlanned;
    }

    public TeamAssignment itnsPlanned(Integer itnsPlanned) {
        this.setItnsPlanned(itnsPlanned);
        return this;
    }

    public void setItnsPlanned(Integer itnsPlanned) {
        this.itnsPlanned = itnsPlanned;
    }

    public Integer getTargetType() {
        return this.targetType;
    }

    public TeamAssignment targetType(Integer targetType) {
        this.setTargetType(targetType);
        return this;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public TeamAssignment longitude(Double longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public TeamAssignment latitude(Double latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Instant getStartDayDate() {
        return this.startDayDate;
    }

    public TeamAssignment startDayDate(Instant startDayDate) {
        this.setStartDayDate(startDayDate);
        return this;
    }

    public void setStartDayDate(Instant startDayDate) {
        this.startDayDate = startDayDate;
    }

    // Inherited createdBy methods
    public TeamAssignment createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public TeamAssignment createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public TeamAssignment lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public TeamAssignment lastModifiedDate(Instant lastModifiedDate) {
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

    public TeamAssignment setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Campaign getCampaign() {
        return this.campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public TeamAssignment campaign(Campaign campaign) {
        this.setCampaign(campaign);
        return this;
    }

    public MVillagesLocations getLocation() {
        return this.location;
    }

    public void setLocation(MVillagesLocations mVillagesLocations) {
        this.location = mVillagesLocations;
    }

    public TeamAssignment location(MVillagesLocations mVillagesLocations) {
        this.setLocation(mVillagesLocations);
        return this;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TeamAssignment team(Team team) {
        this.setTeam(team);
        return this;
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public TeamAssignment warehouse(Warehouse warehouse) {
        this.setWarehouse(warehouse);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeamAssignment)) {
            return false;
        }
        return getId() != null && getId().equals(((TeamAssignment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TeamAssignment{" +
            "id=" + getId() +
            ", phaseNo=" + getPhaseNo() +
            ", fieldCode=" + getFieldCode() +
            ", districtCode=" + getDistrictCode() +
            ", gov='" + getGov() + "'" +
            ", district='" + getDistrict() + "'" +
            ", subdistrict='" + getSubdistrict() + "'" +
            ", village='" + getVillage() + "'" +
            ", subvillage='" + getSubvillage() + "'" +
            ", name='" + getName() + "'" +
            ", dayId=" + getDayId() +
            ", population=" + getPopulation() +
            ", itnsPlanned=" + getItnsPlanned() +
            ", targetType=" + getTargetType() +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", startDayDate='" + getStartDayDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
