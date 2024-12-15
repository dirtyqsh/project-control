package com.dirtyqsh.laba7_mispis.DTO;

import java.time.LocalDate;

public interface ProjectDTO {
    int getProjectId();
    String getProjectName();
    long getProjectCost();
    LocalDate getProjectStart();
    LocalDate getProjectEnd();
    boolean getProjectStatus();
    String getSubcontractors();




    default double getCostInRubles() {
        return getProjectCost() / 100.0;
    }
}
