package com.example.demo.init;

import com.example.demo.service.CountryService;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("com.example.demo.*")
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.entity")
public class BudgetOrganizerApplication {

    @Autowired
    private CountryService countryService;
    @Autowired
    private CurrencyService currencyService;

    public static void main(String[] args) {
        SpringApplication.run(BudgetOrganizerApplication.class, args);

    }

    @PostConstruct
    public void init() {
        countryService.saveCountryData();
        currencyService.saveCurrencyData();
    }
}
