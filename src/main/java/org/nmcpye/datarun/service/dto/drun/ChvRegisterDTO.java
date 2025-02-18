package org.nmcpye.datarun.service.dto.drun;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.nmcpye.datarun.domain.enumeration.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link org.nmcpye.datarun.domain.ChvRegister} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ChvRegisterDTO implements Serializable {

    @Size(max = 11)
    private String uid;

    private String code;

    private String name;

    @NotNull
    private Instant visitDate;

    private Boolean pregnant;

    private MTestResult testResult;

    private MDetectionType detectionType;

    private MSeverity severity;

    private MTreatment treatment;

    private String comment;

    private Instant startEntryTime;

    private Boolean deleted;

    @Max(value = 140)
    private Integer age;

    private Gender gender;

    private AssignmentDTO location;

    private TeamDTO team;

    private ActivityDTO activity;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Instant visitDate) {
        this.visitDate = visitDate;
    }

    public Boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }

    public MTestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(MTestResult testResult) {
        this.testResult = testResult;
    }

    public MDetectionType getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(MDetectionType detectionType) {
        this.detectionType = detectionType;
    }

    public MSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(MSeverity severity) {
        this.severity = severity;
    }

    public MTreatment getTreatment() {
        return treatment;
    }

    public void setTreatment(MTreatment treatment) {
        this.treatment = treatment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getStartEntryTime() {
        return startEntryTime;
    }

    public void setStartEntryTime(Instant startEntryTime) {
        this.startEntryTime = startEntryTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChvRegisterDTO)) {
            return false;
        }

        ChvRegisterDTO chvRegisterDTO = (ChvRegisterDTO) o;
        if (this.uid == null) {
            return false;
        }
        return Objects.equals(this.uid, chvRegisterDTO.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uid);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AssignmentDTO getLocation() {
        return location;
    }

    public void setLocation(AssignmentDTO location) {
        this.location = location;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public ActivityDTO getActivity() {
        return activity;
    }

    public void setActivity(ActivityDTO activity) {
        this.activity = activity;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChvRegisterDTO{" +
            "uid=" + getUid() +
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
            "}";
    }
}
