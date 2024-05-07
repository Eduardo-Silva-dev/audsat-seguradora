package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.model.Car;
import br.com.audsat.seguradora.model.Claim;
import br.com.audsat.seguradora.model.Driver;
import br.com.audsat.seguradora.repository.ClaimRepository;
import br.com.audsat.seguradora.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    @Override
    public boolean checkCarAccident(Car car) {
        return claimRepository.findByCar(car).isEmpty();
    }

    @Override
    public boolean checkDriverAccident(Driver driver) {
        return claimRepository.findByDriver(driver).isEmpty();
    }

    @Override
    public Claim saveClaim(Car car, Driver driver, LocalDate eventDate) {
        return claimRepository.save(Claim.builder()
                .car(car)
                .driver(driver)
                .eventDate(eventDate)
                .build());
    }

    @Override
    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }
}
