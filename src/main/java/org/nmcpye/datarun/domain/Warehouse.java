package org.nmcpye.datarun.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.domain.Persistable;

/**
 * A Warehouse.
 */
@Entity
@Table(name = "warehouse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Warehouse extends AbstractAuditingEntity<Long> implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "wh_name")
    private String whName;

    @Column(name = "description")
    private String description;

    @Column(name = "gps_coordinate")
    private String gpsCoordinate;

    @Column(name = "supervisor_name")
    private String supervisorName;

    @NotNull
    @Column(name = "wh_no", nullable = false)
    private Long whNo;

    @Column(name = "supervisor_mobile")
    private String supervisorMobile;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition
    @Transient
    private boolean isPersisted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "campaignType" }, allowSetters = true)
    private Campaign campaign;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Warehouse id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWhName() {
        return this.whName;
    }

    public Warehouse whName(String whName) {
        this.setWhName(whName);
        return this;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    public String getDescription() {
        return this.description;
    }

    public Warehouse description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpsCoordinate() {
        return this.gpsCoordinate;
    }

    public Warehouse gpsCoordinate(String gpsCoordinate) {
        this.setGpsCoordinate(gpsCoordinate);
        return this;
    }

    public void setGpsCoordinate(String gpsCoordinate) {
        this.gpsCoordinate = gpsCoordinate;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }

    public Warehouse supervisorName(String supervisorName) {
        this.setSupervisorName(supervisorName);
        return this;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Long getWhNo() {
        return this.whNo;
    }

    public Warehouse whNo(Long whNo) {
        this.setWhNo(whNo);
        return this;
    }

    public void setWhNo(Long whNo) {
        this.whNo = whNo;
    }

    public String getSupervisorMobile() {
        return this.supervisorMobile;
    }

    public Warehouse supervisorMobile(String supervisorMobile) {
        this.setSupervisorMobile(supervisorMobile);
        return this;
    }

    public void setSupervisorMobile(String supervisorMobile) {
        this.supervisorMobile = supervisorMobile;
    }

    // Inherited createdBy methods
    public Warehouse createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Warehouse createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Warehouse lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Warehouse lastModifiedDate(Instant lastModifiedDate) {
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

    public Warehouse setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Campaign getCampaign() {
        return this.campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Warehouse campaign(Campaign campaign) {
        this.setCampaign(campaign);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Warehouse)) {
            return false;
        }
        return getId() != null && getId().equals(((Warehouse) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Warehouse{" +
            "id=" + getId() +
            ", whName='" + getWhName() + "'" +
            ", description='" + getDescription() + "'" +
            ", gpsCoordinate='" + getGpsCoordinate() + "'" +
            ", supervisorName='" + getSupervisorName() + "'" +
            ", whNo=" + getWhNo() +
            ", supervisorMobile='" + getSupervisorMobile() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
