package com.example.demo.repository;

import com.example.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT c.borderName, cu.currency FROM Country c JOIN Currency cu ON c.borderCode = cu.country"+
            " WHERE c.countryName = ?1")
    List<String[]> findNeighborCurrencies(String country);


    @Query("SELECT c.borderName FROM Country c JOIN Currency cu ON c.borderCode = cu.country"+
            " WHERE c.countryName = ?1")
    List<String> findNeighborCountries(String country);
}
