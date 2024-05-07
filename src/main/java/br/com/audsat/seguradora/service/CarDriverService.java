package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.model.Car;
import br.com.audsat.seguradora.model.CarDriver;
import br.com.audsat.seguradora.model.Driver;

import java.util.List;
import java.util.Optional;

public interface CarDriverService {

    List<CarDriver> getAllCarDrivers();

    Optional<CarDriver> getCarDriverById(Long id);

    CarDriver saveCarDriver(Driver driver, Car car, boolean isMainDriver);

    void deleteCarDriver(Long id);

}
