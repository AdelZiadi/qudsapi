package org.nmcpye.datarun.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Campaign.
 */
@Entity
@Table(name = "campaign")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "campaign_code", nullable = false)
    private String campaignCode;

    @NotNull
    @Column(name = "campaign_started_date", nullable = false)
    private Instant campaignStartedDate;

    @Min(value = 0)
    @Column(name = "campaign_days")
    private Integer campaignDays;

    @Min(value = 0)
    @Column(name = "campaign_year")
    private Integer campaignYear;

    @Column(name = "campaign_note")
    private String campaignNote;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private CampaignType campaignType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Campaign id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampaignCode() {
        return this.campaignCode;
    }

    public Campaign campaignCode(String campaignCode) {
        this.setCampaignCode(campaignCode);
        return this;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public Instant getCampaignStartedDate() {
        return this.campaignStartedDate;
    }

    public Campaign campaignStartedDate(Instant campaignStartedDate) {
        this.setCampaignStartedDate(campaignStartedDate);
        return this;
    }

    public void setCampaignStartedDate(Instant campaignStartedDate) {
        this.campaignStartedDate = campaignStartedDate;
    }

    public Integer getCampaignDays() {
        return this.campaignDays;
    }

    public Campaign campaignDays(Integer campaignDays) {
        this.setCampaignDays(campaignDays);
        return this;
    }

    public void setCampaignDays(Integer campaignDays) {
        this.campaignDays = campaignDays;
    }

    public Integer getCampaignYear() {
        return this.campaignYear;
    }

    public Campaign campaignYear(Integer campaignYear) {
        this.setCampaignYear(campaignYear);
        return this;
    }

    public void setCampaignYear(Integer campaignYear) {
        this.campaignYear = campaignYear;
    }

    public String getCampaignNote() {
        return this.campaignNote;
    }

    public Campaign campaignNote(String campaignNote) {
        this.setCampaignNote(campaignNote);
        return this;
    }

    public void setCampaignNote(String campaignNote) {
        this.campaignNote = campaignNote;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public Campaign enabled(Boolean enabled) {
        this.setEnabled(enabled);
        return this;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public CampaignType getCampaignType() {
        return this.campaignType;
    }

    public void setCampaignType(CampaignType campaignType) {
        this.campaignType = campaignType;
    }

    public Campaign campaignType(CampaignType campaignType) {
        this.setCampaignType(campaignType);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Campaign)) {
            return false;
        }
        return getId() != null && getId().equals(((Campaign) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Campaign{" +
            "id=" + getId() +
            ", campaignCode='" + getCampaignCode() + "'" +
            ", campaignStartedDate='" + getCampaignStartedDate() + "'" +
            ", campaignDays=" + getCampaignDays() +
            ", campaignYear=" + getCampaignYear() +
            ", campaignNote='" + getCampaignNote() + "'" +
            ", enabled='" + getEnabled() + "'" +
            "}";
    }
}
