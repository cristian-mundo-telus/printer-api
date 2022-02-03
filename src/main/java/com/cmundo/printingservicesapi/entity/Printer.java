package com.cmundo.printingservicesapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer printerId;
    @NotNull(message = "printer name is required")
    private String printerName;
    @NotNull(message = "model is required")
    @Column(unique = true)
    private String model;
    @NotNull(message = "printer status is required")
    private Boolean printerStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "printer", cascade = CascadeType.ALL)
    private List<ServiceEntity> services;




}
