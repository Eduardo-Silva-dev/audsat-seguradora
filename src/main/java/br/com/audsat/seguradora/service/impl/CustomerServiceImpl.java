package br.com.audsat.seguradora.service.impl;

import br.com.audsat.seguradora.model.Customer;
import br.com.audsat.seguradora.model.Driver;
import br.com.audsat.seguradora.repository.CustomerRepository;
import br.com.audsat.seguradora.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer saveCustomer(String name, Driver driver) {
        return customerRepository.save(Customer.builder()
                .name(name)
                .driver(driver)
                .build());
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
