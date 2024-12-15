package com.dirtyqsh.laba7_mispis.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double cost;
    private LocalDate startdate;
    private LocalDate enddate;
    private boolean status;

    public Project() {

    }
}
