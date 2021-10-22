package com.santecsi.apitest.rest;

import com.santecsi.apitest.model.CustomerDTO;
import com.santecsi.apitest.service.CustomerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable final Long dni) {
        return ResponseEntity.ok(customerService.get(dni));
    }

    @PostMapping
    public ResponseEntity<Long> createCustomer(@RequestBody @Valid final CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.create(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<Void> updateCustomer(@PathVariable final Long dni,
            @RequestBody @Valid final CustomerDTO customerDTO) {
        customerService.update(dni, customerDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable final Long dni) {
        customerService.delete(dni);
        return ResponseEntity.noContent().build();
    }

}
