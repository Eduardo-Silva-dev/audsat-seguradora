package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.model.Driver;
import br.com.audsat.seguradora.repository.DriverRepository;
import br.com.audsat.seguradora.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public Driver saveDriver(LocalDate birthDate, long document) {
        return driverRepository.save(Driver.builder()
                .birthdate(birthDate)
                .document(document)
                .build());
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
