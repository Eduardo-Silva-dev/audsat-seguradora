package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.model.Car;
import jakarta.persistence.Column;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Optional<Car> getCarById(Long id);

    Car saveCar(String model, String manufacturer, String year, double fipeValue);

    void deleteCar(Long id);

}
