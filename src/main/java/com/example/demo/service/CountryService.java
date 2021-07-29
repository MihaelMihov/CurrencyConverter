package com.example.demo.service;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public void saveCountryData() {

        String line = "";
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("src/main/resources/database/neighbors.csv"));
            br.readLine();
            while ( (line = br.readLine()) != null) {

                String [] data = line.split(",");
                Country c = new Country();
                c.setCountryCode(data[1]);
                c.setCountryName(data[2]);
                c.setBorderCode(data[3]);
                c.setBorderName(data[4]);
                countryRepository.save(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> findCurrencies(String country){
        return countryRepository.findNeighborCurrencies(country);
    }

    public List<String> findCountries(String country){
        return countryRepository.findNeighborCountries(country);
    }
}
