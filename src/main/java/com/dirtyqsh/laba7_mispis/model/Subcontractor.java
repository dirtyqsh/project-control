package com.dirtyqsh.laba7_mispis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "subcontractor")
public class Subcontractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String country;
    private String address;
    private String phone;

    public Subcontractor() {

    }
}
