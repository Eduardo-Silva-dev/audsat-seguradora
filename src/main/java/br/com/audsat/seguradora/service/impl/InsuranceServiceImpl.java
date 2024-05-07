package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.controller.request.InsuranceRequest;
import br.com.audsat.seguradora.exception.NotFoundException;
import br.com.audsat.seguradora.model.*;
import br.com.audsat.seguradora.repository.InsuranceRepository;
import br.com.audsat.seguradora.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final CarDriverService carDriverService;
    private final CustomerService customerService;
    private final DriverService driverService;
    private final ClaimService claimService;
    private final ModelMapper modelMapper;
    private final CarService carService;

    @Override
    public Insurance findById(Long id) {
        Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);
        return optionalInsurance.orElseThrow(() -> new NotFoundException("Insurance with id " + id + " not found"));
    }

    @Override
    public Insurance save(InsuranceRequest insuranceRequest) {
        Car car = carService.saveCar(insuranceRequest.getYear(), insuranceRequest.getManufacturer(),
                insuranceRequest.getModel(),insuranceRequest.getFipeValue());

        Driver driver = driverService.saveDriver(insuranceRequest.getBirthdate(), insuranceRequest.getDocument());

        carDriverService.saveCarDriver(driver, car, insuranceRequest.isMainDriver());

        claimService.saveClaim(car, driver, insuranceRequest.getEventDate());

        Customer customer = customerService.saveCustomer(insuranceRequest.getNameCustomer(), driver);

        Insurance insurance = Insurance.builder()
                .car(car)
                .customer(customer)
                .creationDate(LocalDate.now())
                .updatedAt(LocalDate.now())
                .active(true)
                .build();

        return insuranceRepository.save(insurance);
    }

    @Override
    public void delete(Long id) {
        insuranceRepository.deleteById(id);
    }

    @Override
    public Insurance update(Long id, Insurance updatedInsurance) {
        Insurance existingInsuranceOptional = findById(id);

        modelMapper.map(updatedInsurance, existingInsuranceOptional);
        existingInsuranceOptional.setUpdatedAt(LocalDate.now());
        return insuranceRepository.save(existingInsuranceOptional);
    }

    @Override
    public Insurance update(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }
}
