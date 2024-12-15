package com.dirtyqsh.laba7_mispis.DTO;

import java.time.LocalDate;

public interface WorkSubcontractorDTO {
    int getWorkSubcontractorId();
    long getWorkSubcontractorCost();
    LocalDate getWorkSubcontractorStart();
    LocalDate getWorkSubcontractorEnd();
    boolean getWorkSubcontractorStatus();
    int getProjectId();
    int getSubcontractorId();

    default double getCostInRubles() {
        return getWorkSubcontractorCost() / 100.0;
    }
}
