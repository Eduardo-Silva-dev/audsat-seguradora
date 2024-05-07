package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.model.Driver;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DriverService {

    List<Driver> getAllDrivers();

    Optional<Driver> getDriverById(Long id);

    Driver saveDriver(LocalDate birthDate, long document);

    void deleteDriver(Long id);
}
