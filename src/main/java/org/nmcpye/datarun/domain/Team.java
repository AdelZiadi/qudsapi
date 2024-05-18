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
 * A Team.
 */
@Entity
@Table(name = "team")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Team extends AbstractAuditingEntity<Long> implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "team_leader")
    private String teamLeader;

    @NotNull
    @Column(name = "team_no", nullable = false)
    private Long teamNo;

    @Column(name = "team_note")
    private String teamNote;

    @Column(name = "team_mobile")
    private String teamMobile;

    @Column(name = "team_no_of_team_workers")
    private Integer teamNoOfTeamWorkers;

    @Column(name = "mobility")
    private String mobility;

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
    private ReviewTeam operationRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "campaign" }, allowSetters = true)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Team id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamLeader() {
        return this.teamLeader;
    }

    public Team teamLeader(String teamLeader) {
        this.setTeamLeader(teamLeader);
        return this;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public Long getTeamNo() {
        return this.teamNo;
    }

    public Team teamNo(Long teamNo) {
        this.setTeamNo(teamNo);
        return this;
    }

    public void setTeamNo(Long teamNo) {
        this.teamNo = teamNo;
    }

    public String getTeamNote() {
        return this.teamNote;
    }

    public Team teamNote(String teamNote) {
        this.setTeamNote(teamNote);
        return this;
    }

    public void setTeamNote(String teamNote) {
        this.teamNote = teamNote;
    }

    public String getTeamMobile() {
        return this.teamMobile;
    }

    public Team teamMobile(String teamMobile) {
        this.setTeamMobile(teamMobile);
        return this;
    }

    public void setTeamMobile(String teamMobile) {
        this.teamMobile = teamMobile;
    }

    public Integer getTeamNoOfTeamWorkers() {
        return this.teamNoOfTeamWorkers;
    }

    public Team teamNoOfTeamWorkers(Integer teamNoOfTeamWorkers) {
        this.setTeamNoOfTeamWorkers(teamNoOfTeamWorkers);
        return this;
    }

    public void setTeamNoOfTeamWorkers(Integer teamNoOfTeamWorkers) {
        this.teamNoOfTeamWorkers = teamNoOfTeamWorkers;
    }

    public String getMobility() {
        return this.mobility;
    }

    public Team mobility(String mobility) {
        this.setMobility(mobility);
        return this;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    // Inherited createdBy methods
    public Team createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public Team createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public Team lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public Team lastModifiedDate(Instant lastModifiedDate) {
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

    public Team setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Campaign getCampaign() {
        return this.campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Team campaign(Campaign campaign) {
        this.setCampaign(campaign);
        return this;
    }

    public ReviewTeam getOperationRoom() {
        return this.operationRoom;
    }

    public void setOperationRoom(ReviewTeam reviewTeam) {
        this.operationRoom = reviewTeam;
    }

    public Team operationRoom(ReviewTeam reviewTeam) {
        this.setOperationRoom(reviewTeam);
        return this;
    }

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Team warehouse(Warehouse warehouse) {
        this.setWarehouse(warehouse);
        return this;
    }

    public User getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(User user) {
        this.userInfo = user;
    }

    public Team userInfo(User user) {
        this.setUserInfo(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Team)) {
            return false;
        }
        return getId() != null && getId().equals(((Team) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Team{" +
            "id=" + getId() +
            ", teamLeader='" + getTeamLeader() + "'" +
            ", teamNo=" + getTeamNo() +
            ", teamNote='" + getTeamNote() + "'" +
            ", teamMobile='" + getTeamMobile() + "'" +
            ", teamNoOfTeamWorkers=" + getTeamNoOfTeamWorkers() +
            ", mobility='" + getMobility() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
