package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String countryCode;
    private String countryName;
    private String borderCode;
    private String borderName;

    public Country() {
    }

    public Country(Integer id, String countryCode, String countryName, String borderCode, String borderName) {
        this.id = id;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.borderCode = borderCode;
        this.borderName = borderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getBorderCode() {
        return borderCode;
    }

    public void setBorderCode(String borderCode) {
        this.borderCode = borderCode;
    }

    public String getBorderName() {
        return borderName;
    }

    public void setBorderName(String borderName) {
        this.borderName = borderName;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", borderCode='" + borderCode + '\'' +
                ", borderName='" + borderName + '\'' +
                '}';
    }
}
