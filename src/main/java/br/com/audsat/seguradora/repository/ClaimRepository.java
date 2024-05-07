package br.com.audsat.seguradora.repository;

import br.com.audsat.seguradora.model.Car;
import br.com.audsat.seguradora.model.Claim;
import br.com.audsat.seguradora.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

    List<Claim> findByCar(Car car);

    List<Claim> findByDriver(Driver driver);
}
