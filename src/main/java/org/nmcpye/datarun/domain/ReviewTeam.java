package org.nmcpye.datarun.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReviewTeam.
 */
@Entity
@Table(name = "review_team")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ReviewTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "progress_orname")
    private String progressOrname;

    @Column(name = "progress_or_user")
    private String progressOrUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReviewTeam id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgressOrname() {
        return this.progressOrname;
    }

    public ReviewTeam progressOrname(String progressOrname) {
        this.setProgressOrname(progressOrname);
        return this;
    }

    public void setProgressOrname(String progressOrname) {
        this.progressOrname = progressOrname;
    }

    public String getProgressOrUser() {
        return this.progressOrUser;
    }

    public ReviewTeam progressOrUser(String progressOrUser) {
        this.setProgressOrUser(progressOrUser);
        return this;
    }

    public void setProgressOrUser(String progressOrUser) {
        this.progressOrUser = progressOrUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReviewTeam)) {
            return false;
        }
        return getId() != null && getId().equals(((ReviewTeam) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReviewTeam{" +
            "id=" + getId() +
            ", progressOrname='" + getProgressOrname() + "'" +
            ", progressOrUser='" + getProgressOrUser() + "'" +
            "}";
    }
}
