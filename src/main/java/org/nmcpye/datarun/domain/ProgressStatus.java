package org.nmcpye.datarun.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProgressStatus.
 */
@Entity
@Table(name = "progress_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProgressStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "progress_status_label")
    private String progressStatusLabel;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProgressStatus id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgressStatusLabel() {
        return this.progressStatusLabel;
    }

    public ProgressStatus progressStatusLabel(String progressStatusLabel) {
        this.setProgressStatusLabel(progressStatusLabel);
        return this;
    }

    public void setProgressStatusLabel(String progressStatusLabel) {
        this.progressStatusLabel = progressStatusLabel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgressStatus)) {
            return false;
        }
        return getId() != null && getId().equals(((ProgressStatus) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProgressStatus{" +
            "id=" + getId() +
            ", progressStatusLabel='" + getProgressStatusLabel() + "'" +
            "}";
    }
}
