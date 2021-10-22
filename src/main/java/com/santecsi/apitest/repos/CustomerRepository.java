package com.santecsi.apitest.repos;

import com.santecsi.apitest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
