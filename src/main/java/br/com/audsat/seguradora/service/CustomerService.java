package br.com.audsat.seguradora.service;

import br.com.audsat.seguradora.model.Customer;
import br.com.audsat.seguradora.model.Driver;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long id);

    Customer saveCustomer(String name, Driver driver);

    void deleteCustomer(Long id);
}
