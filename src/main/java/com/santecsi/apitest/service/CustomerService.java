package com.santecsi.apitest.service;

import com.santecsi.apitest.domain.Customer;
import com.santecsi.apitest.model.CustomerDTO;
import com.santecsi.apitest.repos.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .collect(Collectors.toList());
    }

    public CustomerDTO get(final Long dni) {
        return customerRepository.findById(dni)
                .map(customer -> mapToDTO(customer, new CustomerDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CustomerDTO customerDTO) {
        final Customer customer = new Customer();
        mapToEntity(customerDTO, customer);
        return customerRepository.save(customer).getDni();
    }

    public void update(final Long dni, final CustomerDTO customerDTO) {
        final Customer customer = customerRepository.findById(dni)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(customerDTO, customer);
        customerRepository.save(customer);
    }

    public void delete(final Long dni) {
        customerRepository.deleteById(dni);
    }

    private CustomerDTO mapToDTO(final Customer customer, final CustomerDTO customerDTO) {
        customerDTO.setDni(customer.getDni());
        customerDTO.setName(customer.getName());
        customerDTO.setLastname(customer.getLastname());
        return customerDTO;
    }

    private Customer mapToEntity(final CustomerDTO customerDTO, final Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setLastname(customerDTO.getLastname());
        return customer;
    }

}
