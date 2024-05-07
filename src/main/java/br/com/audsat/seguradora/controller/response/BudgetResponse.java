package br.com.audsat.seguradora.controller.response;

import br.com.audsat.seguradora.model.Car;
import br.com.audsat.seguradora.model.Customer;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
public class BudgetResponse extends RepresentationModel<BudgetResponse> {

    private Long id;

    private Customer customer;

    private LocalDate creationDate;

    private LocalDate updatedAt;

    private Car car;

    private boolean active;

    private double quote;

}
