package com.santecsi.apitest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.santecsi.apitest.domain")
@EnableJpaRepositories("com.santecsi.apitest.repos")
@EnableTransactionManagement
public class DomainConfig {
}
