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
 * A ItnsVillageHousesDetail.
 */
@Entity
@Table(name = "itns_village_houses_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ItnsVillageHousesDetail extends AbstractAuditingEntity<Long> implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 11)
    @Column(name = "uid", length = 11, nullable = false, unique = true)
    private String uid;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "name")
    private String name;

    @Min(value = 0)
    @Column(name = "male")
    private Integer male;

    @Min(value = 0)
    @Column(name = "female")
    private Integer female;

    @Min(value = 0)
    @Column(name = "pregnant")
    private Integer pregnant;

    @Min(value = 0)
    @Column(name = "population")
    private Integer population;

    @Min(value = 0)
    @Column(name = "male_child")
    private Integer maleChild;

    @Min(value = 0)
    @Column(name = "female_child")
    private Integer femaleChild;

    @Min(value = 0)
    @Column(name = "displaced")
    private Integer displaced;

    @Min(value = 0)
    @Column(name = "itns")
    private Integer itns;

    @Size(max = 2000)
    @Column(name = "comment", length = 2000)
    private String comment;

    @Column(name = "submission_uuid")
    private String submissionUuid;

    @Column(name = "house_uuid", unique = true)
    private String houseUuid;

    @Column(name = "deleted")
    private Boolean deleted;

    // Inherited createdBy definition
    // Inherited createdDate definition
    // Inherited lastModifiedBy definition
    // Inherited lastModifiedDate definition
    @Transient
    private boolean isPersisted;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "houseDetails", "progressStatus", "team", "assignment", "activity" }, allowSetters = true)
    private ItnsVillage itnsVillage;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ItnsVillageHousesDetail id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public ItnsVillageHousesDetail uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return this.code;
    }

    public ItnsVillageHousesDetail code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCouponId() {
        return this.couponId;
    }

    public ItnsVillageHousesDetail couponId(Long couponId) {
        this.setCouponId(couponId);
        return this;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getName() {
        return this.name;
    }

    public ItnsVillageHousesDetail name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMale() {
        return this.male;
    }

    public ItnsVillageHousesDetail male(Integer male) {
        this.setMale(male);
        return this;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    public Integer getFemale() {
        return this.female;
    }

    public ItnsVillageHousesDetail female(Integer female) {
        this.setFemale(female);
        return this;
    }

    public void setFemale(Integer female) {
        this.female = female;
    }

    public Integer getPregnant() {
        return this.pregnant;
    }

    public ItnsVillageHousesDetail pregnant(Integer pregnant) {
        this.setPregnant(pregnant);
        return this;
    }

    public void setPregnant(Integer pregnant) {
        this.pregnant = pregnant;
    }

    public Integer getPopulation() {
        return this.population;
    }

    public ItnsVillageHousesDetail population(Integer population) {
        this.setPopulation(population);
        return this;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getMaleChild() {
        return this.maleChild;
    }

    public ItnsVillageHousesDetail maleChild(Integer maleChild) {
        this.setMaleChild(maleChild);
        return this;
    }

    public void setMaleChild(Integer maleChild) {
        this.maleChild = maleChild;
    }

    public Integer getFemaleChild() {
        return this.femaleChild;
    }

    public ItnsVillageHousesDetail femaleChild(Integer femaleChild) {
        this.setFemaleChild(femaleChild);
        return this;
    }

    public void setFemaleChild(Integer femaleChild) {
        this.femaleChild = femaleChild;
    }

    public Integer getDisplaced() {
        return this.displaced;
    }

    public ItnsVillageHousesDetail displaced(Integer displaced) {
        this.setDisplaced(displaced);
        return this;
    }

    public void setDisplaced(Integer displaced) {
        this.displaced = displaced;
    }

    public Integer getItns() {
        return this.itns;
    }

    public ItnsVillageHousesDetail itns(Integer itns) {
        this.setItns(itns);
        return this;
    }

    public void setItns(Integer itns) {
        this.itns = itns;
    }

    public String getComment() {
        return this.comment;
    }

    public ItnsVillageHousesDetail comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSubmissionUuid() {
        return this.submissionUuid;
    }

    public ItnsVillageHousesDetail submissionUuid(String submissionUuid) {
        this.setSubmissionUuid(submissionUuid);
        return this;
    }

    public void setSubmissionUuid(String submissionUuid) {
        this.submissionUuid = submissionUuid;
    }

    public String getHouseUuid() {
        return this.houseUuid;
    }

    public ItnsVillageHousesDetail houseUuid(String houseUuid) {
        this.setHouseUuid(houseUuid);
        return this;
    }

    public void setHouseUuid(String houseUuid) {
        this.houseUuid = houseUuid;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public ItnsVillageHousesDetail deleted(Boolean deleted) {
        this.setDeleted(deleted);
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    // Inherited createdBy methods
    public ItnsVillageHousesDetail createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public ItnsVillageHousesDetail createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public ItnsVillageHousesDetail lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public ItnsVillageHousesDetail lastModifiedDate(Instant lastModifiedDate) {
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

    public ItnsVillageHousesDetail setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public ItnsVillage getItnsVillage() {
        return this.itnsVillage;
    }

    public void setItnsVillage(ItnsVillage itnsVillage) {
        this.itnsVillage = itnsVillage;
    }

    public ItnsVillageHousesDetail itnsVillage(ItnsVillage itnsVillage) {
        this.setItnsVillage(itnsVillage);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItnsVillageHousesDetail)) {
            return false;
        }
        return getId() != null && getId().equals(((ItnsVillageHousesDetail) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ItnsVillageHousesDetail{" +
            "id=" + getId() +
            ", uid='" + getUid() + "'" +
            ", code='" + getCode() + "'" +
            ", couponId=" + getCouponId() +
            ", name='" + getName() + "'" +
            ", male=" + getMale() +
            ", female=" + getFemale() +
            ", pregnant=" + getPregnant() +
            ", population=" + getPopulation() +
            ", maleChild=" + getMaleChild() +
            ", femaleChild=" + getFemaleChild() +
            ", displaced=" + getDisplaced() +
            ", itns=" + getItns() +
            ", comment='" + getComment() + "'" +
            ", submissionUuid='" + getSubmissionUuid() + "'" +
            ", houseUuid='" + getHouseUuid() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
