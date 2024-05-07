package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.model.Car;
import br.com.audsat.seguradora.model.Claim;
import br.com.audsat.seguradora.model.Driver;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClaimService {

    List<Claim> getAllClaims();

    Optional<Claim> getClaimById(Long id);

    boolean checkCarAccident(Car car);

    boolean checkDriverAccident(Driver driver);

    Claim saveClaim(Car car, Driver driver, LocalDate eventDate);

    void deleteClaim(Long id);

}
