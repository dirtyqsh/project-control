package com.dirtyqsh.laba7_mispis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "worksubcontractor")
public class WorkSubcontractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double cost;
    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;
    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "id_subcontractor")
    private Subcontractor subcontractor;

    public WorkSubcontractor() {

    }
}
