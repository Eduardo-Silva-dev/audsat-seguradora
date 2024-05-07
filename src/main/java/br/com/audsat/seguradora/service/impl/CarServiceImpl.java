package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.model.Car;
import br.com.audsat.seguradora.repository.CarRepository;
import br.com.audsat.seguradora.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car saveCar(String model, String manufacturer, String year, double fipeValue) {
        return carRepository.save(Car.builder()
                .year(year)
                .manufacturer(manufacturer)
                .fipeValue(fipeValue)
                .model(model)
                .build());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
