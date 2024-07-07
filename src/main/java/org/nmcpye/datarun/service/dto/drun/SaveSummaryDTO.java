package org.nmcpye.datarun.service.dto.drun;

import java.util.List;
import java.util.Map;

public class SaveSummaryDTO {
    private List<String> successfulUids;
    private Map<String, String> failedUids; // Key is UID, value is error message

    // Getters and Setters

    public List<String> getSuccessfulUids() {
        return successfulUids;
    }

    public void setSuccessfulUids(List<String> successfulUids) {
        this.successfulUids = successfulUids;
    }

    public Map<String, String> getFailedUids() {
        return failedUids;
    }

    public void setFailedUids(Map<String, String> failedUids) {
        this.failedUids = failedUids;
    }
}

