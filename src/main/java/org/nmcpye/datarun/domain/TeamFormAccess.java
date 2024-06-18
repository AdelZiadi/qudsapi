package org.nmcpye.datarun.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.nmcpye.datarun.domain.enumeration.MSessionSubject;
import org.nmcpye.datarun.domain.enumeration.SyncableStatus;

/**
 * A TeamFormAccess.
 */
@Entity
@Table(name = "team_form_access")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TeamFormAccess implements Serializable {

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

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "start_entry_time")
    private Instant startEntryTime;

    @Column(name = "finished_entry_time")
    private Instant finishedEntryTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SyncableStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TeamFormAccess id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return this.uid;
    }

    public TeamFormAccess uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return this.code;
    }

    public TeamFormAccess code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public TeamFormAccess name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getSessionDate() {
        return this.sessionDate;
    }

    public TeamFormAccess sessionDate(Instant sessionDate) {
        this.setSessionDate(sessionDate);
        return this;
    }

    public void setSessionDate(Instant sessionDate) {
        this.sessionDate = sessionDate;
    }

    public MSessionSubject getSubject() {
        return this.subject;
    }

    public TeamFormAccess subject(MSessionSubject subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(MSessionSubject subject) {
        this.subject = subject;
    }

    public Integer getSessions() {
        return this.sessions;
    }

    public TeamFormAccess sessions(Integer sessions) {
        this.setSessions(sessions);
        return this;
    }

    public void setSessions(Integer sessions) {
        this.sessions = sessions;
    }

    public Integer getPeople() {
        return this.people;
    }

    public TeamFormAccess people(Integer people) {
        this.setPeople(people);
        return this;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getComment() {
        return this.comment;
    }

    public TeamFormAccess comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDeleted() {
        return this.deleted;
    }

    public TeamFormAccess deleted(Boolean deleted) {
        this.setDeleted(deleted);
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Instant getStartEntryTime() {
        return this.startEntryTime;
    }

    public TeamFormAccess startEntryTime(Instant startEntryTime) {
        this.setStartEntryTime(startEntryTime);
        return this;
    }

    public void setStartEntryTime(Instant startEntryTime) {
        this.startEntryTime = startEntryTime;
    }

    public Instant getFinishedEntryTime() {
        return this.finishedEntryTime;
    }

    public TeamFormAccess finishedEntryTime(Instant finishedEntryTime) {
        this.setFinishedEntryTime(finishedEntryTime);
        return this;
    }

    public void setFinishedEntryTime(Instant finishedEntryTime) {
        this.finishedEntryTime = finishedEntryTime;
    }

    public SyncableStatus getStatus() {
        return this.status;
    }

    public TeamFormAccess status(SyncableStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(SyncableStatus status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeamFormAccess)) {
            return false;
        }
        return getId() != null && getId().equals(((TeamFormAccess) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TeamFormAccess{" +
            "id=" + getId() +
            ", uid='" + getUid() + "'" +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", sessionDate='" + getSessionDate() + "'" +
            ", subject='" + getSubject() + "'" +
            ", sessions=" + getSessions() +
            ", people=" + getPeople() +
            ", comment='" + getComment() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", startEntryTime='" + getStartEntryTime() + "'" +
            ", finishedEntryTime='" + getFinishedEntryTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
