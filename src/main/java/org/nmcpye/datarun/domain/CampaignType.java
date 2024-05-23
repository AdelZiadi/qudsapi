package org.nmcpye.datarun.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CampaignType.
 */
@Entity
@Table(name = "campaign_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CampaignType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "campaign_type", nullable = false, unique = true)
    private String campaignType;

    @Column(name = "description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CampaignType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampaignType() {
        return this.campaignType;
    }

    public CampaignType campaignType(String campaignType) {
        this.setCampaignType(campaignType);
        return this;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    public String getDescription() {
        return this.description;
    }

    public CampaignType description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignType)) {
            return false;
        }
        return getId() != null && getId().equals(((CampaignType) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CampaignType{" +
            "id=" + getId() +
            ", campaignType='" + getCampaignType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
