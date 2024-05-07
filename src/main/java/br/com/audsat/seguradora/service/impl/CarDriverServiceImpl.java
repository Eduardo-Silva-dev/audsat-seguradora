package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.model.Car;
import br.com.audsat.seguradora.model.CarDriver;
import br.com.audsat.seguradora.model.Driver;
import br.com.audsat.seguradora.repository.CarDriverRepository;
import br.com.audsat.seguradora.service.CarDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarDriverServiceImpl implements CarDriverService {

    private final CarDriverRepository carDriverRepository;

    @Override
    public List<CarDriver> getAllCarDrivers() {
        return carDriverRepository.findAll();
    }

    @Override
    public Optional<CarDriver> getCarDriverById(Long id) {
        return carDriverRepository.findById(id);
    }

    @Override
    public CarDriver saveCarDriver(Driver driver, Car car, boolean isMainDriver) {
        return carDriverRepository.save(CarDriver.builder()
                .car(car)
                .driver(driver)
                .mainDriver(isMainDriver)
                .build());
    }

    @Override
    public void deleteCarDriver(Long id) {
        carDriverRepository.deleteById(id);
    }
}
