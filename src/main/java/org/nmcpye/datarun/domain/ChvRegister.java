package org.nmcpye.datarun.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nmcpye.datarun.domain.enumeration.MDetectionType;
import org.nmcpye.datarun.domain.enumeration.MSeverity;
import org.nmcpye.datarun.domain.enumeration.MTestResult;
import org.nmcpye.datarun.domain.enumeration.MTreatment;
import org.springframework.data.domain.Persistable;

/**
 * A ChvRegister.
 */
@Entity
@Table(name = "chv_register")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChvRegister extends AbstractAuditingEntity<Long> implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 11)
    @Column(name = "uid", length = 11, unique = true)
    private String uid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "visit_date", nullable = false)
    private Instant visitDate;

    @Column(name = "pregnant")
    private Boolean pregnant;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_result")
    private MTestResult testResult;

    @Enumerated(EnumType.STRING)
    @Column(name = "detection_type")
    private MDetectionType detectionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "severity")
    private MSeverity severity;

    @Enumerated(EnumType.STRING)
    @Column(name = "treatment")
    private MTreatment treatment;

    @Column(name = "comment")
    private String comment;

    @Column(name = "start_entry_time")
    private Instant startEntryTime;

    @Column(name = "deleted")
    private Boolean deleted;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition
    @Transient
    private boolean isPersisted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "location" }, allowSetters = true)
    private PatientInfo patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "activity", "operationRoom", "warehouse", "userInfo", "assignments" }, allowSetters = true)
    private Team team;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChvRegister id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public ChvRegister uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return this.code;
    }

    public ChvRegister code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public ChvRegister name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getVisitDate() {
        return this.visitDate;
    }

    public ChvRegister visitDate(Instant visitDate) {
        this.setVisitDate(visitDate);
        return this;
    }

    public void setVisitDate(Instant visitDate) {
        this.visitDate = visitDate;
    }

    public Boolean getPregnant() {
        return this.pregnant;
    }

    public ChvRegister pregnant(Boolean pregnant) {
        this.setPregnant(pregnant);
        return this;
    }

    public void setPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }

    public MTestResult getTestResult() {
        return this.testResult;
    }

    public ChvRegister testResult(MTestResult testResult) {
        this.setTestResult(testResult);
        return this;
    }

    public void setTestResult(MTestResult testResult) {
        this.testResult = testResult;
    }

    public MDetectionType getDetectionType() {
        return this.detectionType;
    }

    public ChvRegister detectionType(MDetectionType detectionType) {
        this.setDetectionType(detectionType);
        return this;
    }

    public void setDetectionType(MDetectionType detectionType) {
        this.detectionType = detectionType;
    }

    public MSeverity getSeverity() {
        return this.severity;
    }

    public ChvRegister severity(MSeverity severity) {
        this.setSeverity(severity);
        return this;
    }

    public void setSeverity(MSeverity severity) {
        this.severity = severity;
    }

    public MTreatment getTreatment() {
        return this.treatment;
    }

    public ChvRegister treatment(MTreatment treatment) {
        this.setTreatment(treatment);
        return this;
    }

    public void setTreatment(MTreatment treatment) {
        this.treatment = treatment;
    }

    public String getComment() {
        return this.comment;
    }

    public ChvRegister comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getStartEntryTime() {
        return this.startEntryTime;
    }

    public ChvRegister startEntryTime(Instant startEntryTime) {
        this.setStartEntryTime(startEntryTime);
        return this;
    }

    public void setStartEntryTime(Instant startEntryTime) {
        this.startEntryTime = startEntryTime;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public ChvRegister deleted(Boolean deleted) {
        this.setDeleted(deleted);
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    // Inherited createdBy methods
    public ChvRegister createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public ChvRegister createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public ChvRegister lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public ChvRegister lastModifiedDate(Instant lastModifiedDate) {
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

    public ChvRegister setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public PatientInfo getPatient() {
        return this.patient;
    }

    public void setPatient(PatientInfo patientInfo) {
        this.patient = patientInfo;
    }

    public ChvRegister patient(PatientInfo patientInfo) {
        this.setPatient(patientInfo);
        return this;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ChvRegister team(Team team) {
        this.setTeam(team);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChvRegister)) {
            return false;
        }
        return getId() != null && getId().equals(((ChvRegister) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChvRegister{" +
            "id=" + getId() +
            ", uid='" + getUid() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", visitDate='" + getVisitDate() + "'" +
            ", pregnant='" + getPregnant() + "'" +
            ", testResult='" + getTestResult() + "'" +
            ", detectionType='" + getDetectionType() + "'" +
            ", severity='" + getSeverity() + "'" +
            ", treatment='" + getTreatment() + "'" +
            ", comment='" + getComment() + "'" +
            ", startEntryTime='" + getStartEntryTime() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
