package br.com.audsat.seguradora.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InsuranceRequest {

    private String nameCustomer;

    private String model;

    private String manufacturer;

    private String year;

    private float fipeValue;

    private long document;

    private LocalDate birthdate;

    private boolean isMainDriver;

    private LocalDate eventDate;
}