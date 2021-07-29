package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public void saveCurrencyData() {

        String line = "";
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/resources/database/currencies.csv"));
            br.readLine();
            while ( (line = br.readLine()) != null) {

                String [] data = line.split(",");
                Currency c = new Currency();
                c.setCountry(data[1]);
                c.setCurrency(data[2]);
                currencyRepository.save(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
