package org.nmcpye.datarun.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nmcpye.datarun.domain.enumeration.MSessionSubject;
import org.springframework.data.domain.Persistable;

/**
 * A ChvSessions.
 */
@Entity
@Table(name = "chv_sessions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "new" })
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChvSessions extends AbstractAuditingEntity<Long> implements Serializable, Persistable<Long> {

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
    @Column(name = "session_date", nullable = false)
    private Instant sessionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject")
    private MSessionSubject subject;

    @NotNull
    @Column(name = "sessions", nullable = false)
    private Integer sessions;

    @NotNull
    @Column(name = "people", nullable = false)
    private Integer people;

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
    @JsonIgnoreProperties(value = { "activity", "operationRoom", "warehouse", "userInfo", "assignments" }, allowSetters = true)
    private Team team;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ChvSessions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public ChvSessions uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return this.code;
    }

    public ChvSessions code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public ChvSessions name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getSessionDate() {
        return this.sessionDate;
    }

    public ChvSessions sessionDate(Instant sessionDate) {
        this.setSessionDate(sessionDate);
        return this;
    }

    public void setSessionDate(Instant sessionDate) {
        this.sessionDate = sessionDate;
    }

    public MSessionSubject getSubject() {
        return this.subject;
    }

    public ChvSessions subject(MSessionSubject subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(MSessionSubject subject) {
        this.subject = subject;
    }

    public Integer getSessions() {
        return this.sessions;
    }

    public ChvSessions sessions(Integer sessions) {
        this.setSessions(sessions);
        return this;
    }

    public void setSessions(Integer sessions) {
        this.sessions = sessions;
    }

    public Integer getPeople() {
        return this.people;
    }

    public ChvSessions people(Integer people) {
        this.setPeople(people);
        return this;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getComment() {
        return this.comment;
    }

    public ChvSessions comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getStartEntryTime() {
        return this.startEntryTime;
    }

    public ChvSessions startEntryTime(Instant startEntryTime) {
        this.setStartEntryTime(startEntryTime);
        return this;
    }

    public void setStartEntryTime(Instant startEntryTime) {
        this.startEntryTime = startEntryTime;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public ChvSessions deleted(Boolean deleted) {
        this.setDeleted(deleted);
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    // Inherited createdBy methods
    public ChvSessions createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    // Inherited createdDate methods
    public ChvSessions createdDate(Instant createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    // Inherited lastModifiedBy methods
    public ChvSessions lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    // Inherited lastModifiedDate methods
    public ChvSessions lastModifiedDate(Instant lastModifiedDate) {
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

    public ChvSessions setIsPersisted() {
        this.isPersisted = true;
        return this;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ChvSessions team(Team team) {
        this.setTeam(team);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChvSessions)) {
            return false;
        }
        return getId() != null && getId().equals(((ChvSessions) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChvSessions{" +
            "id=" + getId() +
            ", uid='" + getUid() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", sessionDate='" + getSessionDate() + "'" +
            ", subject='" + getSubject() + "'" +
            ", sessions=" + getSessions() +
            ", people=" + getPeople() +
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
